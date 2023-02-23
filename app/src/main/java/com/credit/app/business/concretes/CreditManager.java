package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CreditService;
import com.credit.app.business.abstracts.IndividualCustomerService;
import com.credit.app.business.constants.Constants;
import com.credit.app.business.constants.CreditStatusEnum;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.requests.credit.AddCreditRequest;
import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.responses.credit.CreditResultResponse;
import com.credit.app.business.responses.credit.GetAllCreditResponse;
import com.credit.app.business.responses.credit.GetByIdCreditResponse;
import com.credit.app.business.utilities.creditScore.CreditScoreService;
import com.credit.app.core.utilities.business.BusinessRules;
import com.credit.app.core.utilities.mapper.MapperUtil;
import com.credit.app.core.utilities.results.ErrorResult;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.SuccessResult;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.core.utilities.results.dataResults.ErrorDataResult;
import com.credit.app.core.utilities.results.dataResults.SuccessDataResult;
import com.credit.app.dataAccess.abstracts.CreditDao;
import com.credit.app.entities.concretes.Credit;
import com.credit.app.entities.concretes.IndividualCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreditManager implements CreditService {

    private static final String MESSAGE = "Credit(s)";
    private CreditDao creditDao;
    private IndividualCustomerService indCustomerService;
    private CreditScoreService creditScoreService;

    @Override
    public DataResult<Collection<GetAllCreditResponse>> getAll() {
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED,
                MapperUtil.mapAll(creditDao.findAll(), GetAllCreditResponse.class));
    }

    @Override
    public DataResult<GetByIdCreditResponse> getById(Long id) {
        Optional<Credit> result = creditDao.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED,
                    MapperUtil.map(result.get(), GetByIdCreditResponse.class));
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public DataResult<CreditResultResponse> add(AddCreditRequest addCreditRequest) {

        final int creditScore = getCreditScore(addCreditRequest.getNationalId());

        Result result = BusinessRules.run(checkCreditScore(creditScore));
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), new CreditResultResponse(0, CreditStatusEnum.DENIED));
        }

        final IndividualCustomer customer = getCustomerDetail(addCreditRequest);
        final double income = addCreditRequest.getIncome();
        final double guarantee = addCreditRequest.getGuarantee();
        final double creditAmount = generateCredit(creditScore, income, guarantee);
        creditDao.save(new Credit(creditAmount, customer));
        return new SuccessDataResult<>(MESSAGE + Messages.ADDED,
                new CreditResultResponse(creditAmount, CreditStatusEnum.APPROVED));
    }

    @Override
    public Result update(Credit credit) {
        creditDao.save(credit);
        return new SuccessResult(MESSAGE + Messages.UPDATED);
    }

    @Override
    public Result delete(Long id) {
        creditDao.deleteById(id);
        return new SuccessResult(MESSAGE + Messages.DELETED);
    }

    // Business functions

    private IndividualCustomer getCustomerDetail(AddCreditRequest request) {
        DataResult<IndividualCustomer> result = indCustomerService.getByNationalId(request.getNationalId());
        if (!result.isSuccess()) {
            return indCustomerService.add(MapperUtil.map(request, AddIndividualCustomerRequest.class)).getData();
        }
        return result.getData();
    }

    private int getCreditScore(String nationalId) {
        return creditScoreService.getCreditScore(nationalId);
    }

    private double generateCredit(int creditScore, double income, double guarantee) {
        if (creditScore < Constants.CREDIT_SCORE_LIMIT) {
            return calculateCreditAmount(income, guarantee);
        } else {
            return (income * Constants.CREDIT_LIMIT_MULTIPLIER) + (guarantee * Constants.MAX_GUARANTEE);
        }
    }

    private double calculateCreditAmount(double income, double guarantee) {
        if (income < Constants.INCOME_MIN) {
            return Constants.MIN_CREDIT_AMOUNT + (guarantee * Constants.MIN_GUARANTEE);
        } else if (income < Constants.INCOME_MAX) {
            return Constants.MID_CREDIT_AMOUNT + (guarantee * Constants.LOW_GUARANTEE);
        } else {
            return (income * Constants.CREDIT_LIMIT_MULTIPLIER / Constants.CREDIT_LIMIT_DIVISOR)
                    + (guarantee * Constants.MID_GUARANTEE);
        }
    }

    // Business functions end

    // Business rules

    private Result checkCreditScore(int creditScore) {
        if (creditScore > Constants.CREDIT_SCORE_MIN) {
            return new SuccessResult();
        }
        return new ErrorResult(Messages.INSUFFICIENT_CREDIT_SCORE);
    }
    // Business rules end
}

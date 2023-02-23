package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CreditService;
import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.constants.Constants;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.requests.credit.AddCreditRequest;
import com.credit.app.business.responses.credit.CreditResultResponse;
import com.credit.app.business.responses.credit.GetAllCreditResponse;
import com.credit.app.business.responses.credit.GetByIdCreditResponse;
import com.credit.app.business.responses.individualCustomer.GetByNationalIdIndividualCustomerResponse;
import com.credit.app.business.utilities.creditScore.CreditScoreService;
import com.credit.app.core.utilities.mapper.MapperUtil;
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
    private CustomerService customerService;
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

        final int creditScore = checkCreditScore(addCreditRequest.getNationalId());
        // TODO refactor
        final double income = addCreditRequest.getIncome();
        final double guarantee = addCreditRequest.getGuarantee();
        // -----------------------
        if (creditScore > Constants.CREDIT_SCORE_MIN) {
            final IndividualCustomer customer = getCustomerDetails(addCreditRequest.getNationalId());
            final Credit credit = creditDao.save(new Credit(generateCredit(creditScore, income, guarantee), customer));
            return new SuccessDataResult<>(MESSAGE + Messages.ADDED,
                    MapperUtil.map(credit, CreditResultResponse.class));
        }
        return new ErrorDataResult<>(Messages.INSUFFICIENT_CREDIT_SCORE, null);
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

    private int checkCreditScore(String nationalId) {
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
            return (income * Constants.CREDIT_LIMIT_MULTIPLIER / 2) + (guarantee * Constants.MID_GUARANTEE);
        }
    }

    private IndividualCustomer getCustomerDetails(String nationalId) {
        DataResult<GetByNationalIdIndividualCustomerResponse> result = customerService.getByNationalId(nationalId);
        if (Boolean.TRUE.equals(result.getSuccess())) {
            return MapperUtil.map(result.getData(), IndividualCustomer.class);
        }
        throw new IllegalArgumentException();
    }
}

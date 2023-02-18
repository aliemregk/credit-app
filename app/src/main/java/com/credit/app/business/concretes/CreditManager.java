package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CreditService;
import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.constants.Constants;
import com.credit.app.business.constants.Messages;
import com.credit.app.business.utilities.creditScore.CreditScoreService;
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
    private CustomerService customerService;
    private CreditScoreService creditScoreService;

    @Override
    public DataResult<Collection<Credit>> getAll() {
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED, creditDao.findAll());
    }

    @Override
    public DataResult<Credit> getById(Long id) {
        Optional<Credit> result = creditDao.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, result.get());
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public DataResult<Credit> add(String nationalId) {

        final int creditScore = checkCreditScore(nationalId);
        // TODO refactor
        double income = 5000;
        double guarantee = 0;
        // -----------------------
        if (creditScore > Constants.CREDIT_SCORE_MIN) {
            final IndividualCustomer customer = getCustomerDetails(nationalId);
            final Credit credit = creditDao.save(new Credit(generateCredit(creditScore, income, guarantee), customer));
            return new SuccessDataResult<>(MESSAGE + Messages.ADDED, credit);
        }
        return new ErrorDataResult<>(Messages.INSUFFICIENT_CREDIT_SCORE, null);
    }

    @Override
    public DataResult<Credit> update(Credit credit) {
        Optional<Credit> result = creditDao.findById(credit.getId());
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.UPDATED, creditDao.save(credit));
        }
        return new ErrorDataResult<>(Messages.UPDATED_ERR, null);
    }

    @Override
    public Result delete(Long id) {
        Optional<Credit> result = creditDao.findById(id);
        if (result.isPresent()) {
            creditDao.delete(result.get());
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.DELETED_ERR);
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
        DataResult<IndividualCustomer> result = customerService.getByNationalId(nationalId);
        if (Boolean.TRUE.equals(result.getSuccess())) {
            return result.getData();
        }
        throw new IllegalArgumentException();
    }
}

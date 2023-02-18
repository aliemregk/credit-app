package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.constants.Messages;
import com.credit.app.core.utilities.results.ErrorResult;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.SuccessResult;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.core.utilities.results.dataResults.ErrorDataResult;
import com.credit.app.core.utilities.results.dataResults.SuccessDataResult;
import com.credit.app.dataAccess.abstracts.IndividualCustomerDao;
import com.credit.app.entities.concretes.IndividualCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private static final String MESSAGE = "Customer(s)";
    private IndividualCustomerDao individualCustomerDao;

    @Override
    public DataResult<Collection<IndividualCustomer>> getAll() {
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED, individualCustomerDao.findAll());
    }

    @Override
    public DataResult<IndividualCustomer> getById(Long id) {
        Optional<IndividualCustomer> result = individualCustomerDao.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, result.get());
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public DataResult<IndividualCustomer> add(IndividualCustomer customer) {
        return new SuccessDataResult<>(MESSAGE + Messages.ADDED, individualCustomerDao.save(customer));
    }

    @Override
    public DataResult<IndividualCustomer> update(IndividualCustomer customer) {
        Optional<IndividualCustomer> result = individualCustomerDao.findById(customer.getId());
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.UPDATED, individualCustomerDao.save(customer));
        }
        return new ErrorDataResult<>(Messages.UPDATED_ERR, null);
    }

    @Override
    public Result delete(Long id) {
        Optional<IndividualCustomer> result = individualCustomerDao.findById(id);
        if (result.isPresent()) {
            individualCustomerDao.delete(result.get());
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.DELETED_ERR);
    }

    @Override
    public DataResult<IndividualCustomer> getByNationalId(String nationalId) {
        Optional<IndividualCustomer> customer = individualCustomerDao.getByNationalId(nationalId);
        if (customer.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, customer.get());
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

}

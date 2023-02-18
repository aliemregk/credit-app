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
import com.credit.app.dataAccess.abstracts.CustomerDao;
import com.credit.app.entities.concretes.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private static final String MESSAGE = "Customer(s)";
    private CustomerDao customerDao;

    @Override
    public DataResult<Collection<Customer>> getAll() {
        return new SuccessDataResult<>(MESSAGE + Messages.LISTED, customerDao.findAll());
    }

    @Override
    public DataResult<Customer> getById(Long id) {
        Optional<Customer> result = customerDao.findById(id);
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, result.get());
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

    @Override
    public DataResult<Customer> add(Customer customer) {
        return new SuccessDataResult<>(MESSAGE + Messages.ADDED, customerDao.save(customer));
    }

    @Override
    public DataResult<Customer> update(Customer customer) {
        Optional<Customer> result = customerDao.findById(customer.getId());
        if (result.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.UPDATED, customerDao.save(customer));
        }
        return new ErrorDataResult<>(Messages.UPDATED_ERR, null);
    }

    @Override
    public Result delete(Long id) {
        Optional<Customer> result = customerDao.findById(id);
        if (result.isPresent()) {
            customerDao.delete(result.get());
            return new SuccessResult(MESSAGE + Messages.DELETED);
        }
        return new ErrorResult(MESSAGE + Messages.DELETED_ERR);
    }

    @Override
    public DataResult<Customer> getByNationalId(String nationalId) {
        Optional<Customer> customer = customerDao.getByNationalId(nationalId);
        if (customer.isPresent()) {
            return new SuccessDataResult<>(MESSAGE + Messages.LISTED, customer.get());
        }
        return new ErrorDataResult<>(MESSAGE + Messages.NOT_FOUND, null);
    }

}

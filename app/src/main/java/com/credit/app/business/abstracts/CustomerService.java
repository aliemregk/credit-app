package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.Customer;

public interface CustomerService {

    DataResult<Collection<Customer>> getAll();

    DataResult<Customer> getById(Long id);

    DataResult<Customer> add(Customer customer);

    DataResult<Customer> update(Customer customer);

    Result delete(Long id);

    DataResult<Customer> getByNationalId(String nationalId);
}

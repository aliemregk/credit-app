package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.IndividualCustomer;

public interface CustomerService {

    DataResult<Collection<IndividualCustomer>> getAll();

    DataResult<IndividualCustomer> getById(Long id);

    DataResult<IndividualCustomer> add(IndividualCustomer customer);

    DataResult<IndividualCustomer> update(IndividualCustomer customer);

    Result delete(Long id);

    DataResult<IndividualCustomer> getByNationalId(String nationalId);
}

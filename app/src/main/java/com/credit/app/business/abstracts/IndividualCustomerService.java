package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.IndividualCustomer;

public interface IndividualCustomerService {

    DataResult<Collection<GetAllIndividualCustomerResponse>> getAll();

    DataResult<GetByIdIndividualCustomerResponse> getById(Long id);

    DataResult<IndividualCustomer> add(AddIndividualCustomerRequest customer);

    Result update(UpdateIndividualCustomerRequest customer);

    Result delete(Long id);

    DataResult<IndividualCustomer> getByNationalId(String nationalId);
}

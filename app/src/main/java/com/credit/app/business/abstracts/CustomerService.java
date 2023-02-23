package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByNationalIdIndividualCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;

public interface CustomerService {

    DataResult<Collection<GetAllIndividualCustomerResponse>> getAll();

    DataResult<GetByIdIndividualCustomerResponse> getById(Long id);

    Result add(AddIndividualCustomerRequest customer);

    Result update(UpdateIndividualCustomerRequest customer);

    Result delete(Long id);

    DataResult<GetByNationalIdIndividualCustomerResponse> getByNationalId(String nationalId);
}

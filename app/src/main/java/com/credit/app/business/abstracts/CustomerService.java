package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.business.responses.customer.GetAllCustomerResponse;
import com.credit.app.business.responses.customer.GetByIdCustomerResponse;
import com.credit.app.core.utilities.results.dataResults.DataResult;

public interface CustomerService {

    DataResult<Collection<GetAllCustomerResponse>> getAll();

    DataResult<GetByIdCustomerResponse> getById(Long id);

}

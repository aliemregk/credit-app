package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.business.requests.corporateCustomer.AddCorporateCustomerRequest;
import com.credit.app.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.credit.app.business.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.CorporateCustomer;

public interface CorporateCustomerService {

    DataResult<Collection<GetAllCorporateCustomerResponse>> getAll();

    DataResult<CorporateCustomer> add(AddCorporateCustomerRequest addRequest);

    Result update(UpdateCorporateCustomerRequest updateRequest);

    Result delete(Long id);

    DataResult<CorporateCustomer> getByTaxNumber(String taxNumber);
}

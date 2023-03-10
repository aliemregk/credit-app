package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.business.requests.credit.AddCreditForCorporateRequest;
import com.credit.app.business.requests.credit.AddCreditForIndividualRequest;
import com.credit.app.business.responses.credit.CreditResultResponse;
import com.credit.app.business.responses.credit.GetAllCreditResponse;
import com.credit.app.business.responses.credit.GetByIdCreditResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.Credit;

public interface CreditService {

    DataResult<Collection<GetAllCreditResponse>> getAll();

    DataResult<GetByIdCreditResponse> getById(Long id);

    DataResult<CreditResultResponse> addCreditForIndividual(AddCreditForIndividualRequest addIndividualCreditRequest);

    DataResult<CreditResultResponse> addCreditForCorporate(AddCreditForCorporateRequest addCorporateCreditRequest);

    Result update(Credit credit);

    Result delete(Long id);
}

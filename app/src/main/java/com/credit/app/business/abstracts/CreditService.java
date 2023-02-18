package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.Credit;

public interface CreditService {

    DataResult<Collection<Credit>> getAll();

    DataResult<Credit> getById(Long id);

    DataResult<Credit> add(String nationalId);

    DataResult<Credit> update(Credit credit);

    Result delete(Long id);
}

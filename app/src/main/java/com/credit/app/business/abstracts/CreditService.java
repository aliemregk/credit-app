package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.entities.concretes.Credit;

public interface CreditService {

    Collection<Credit> getAll();

    Credit getById(Long id);

    Credit add(Credit credit);

    Credit update(Credit credit);

    void delete(Long id);
}

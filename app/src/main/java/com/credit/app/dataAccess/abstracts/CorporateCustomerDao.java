package com.credit.app.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.app.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Long> {

    Optional<CorporateCustomer> getByTaxNumber(String taxNumber);

}

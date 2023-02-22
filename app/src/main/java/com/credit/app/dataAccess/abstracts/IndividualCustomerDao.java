package com.credit.app.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.credit.app.entities.concretes.IndividualCustomer;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Long> {

    Optional<IndividualCustomer> getByNationalId(String nationalId);
}

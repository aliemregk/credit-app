package com.credit.app.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.app.entities.concretes.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

    Optional<Customer> getByNationalId(String nationalId);
}

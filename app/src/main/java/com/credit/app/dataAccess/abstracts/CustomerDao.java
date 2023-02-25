package com.credit.app.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.app.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}

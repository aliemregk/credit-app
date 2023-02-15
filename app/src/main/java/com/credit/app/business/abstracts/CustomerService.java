package com.credit.app.business.abstracts;

import java.util.Collection;

import com.credit.app.entities.concretes.Customer;

public interface CustomerService {
    Collection<Customer> getAll();

    Customer getById(Long id);

    Customer add(Customer customer);

    Customer update(Customer customer);

    void delete(Long id);
}

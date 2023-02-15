package com.credit.app.business.concretes;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.dataAccess.abstracts.CustomerDao;
import com.credit.app.entities.concretes.Customer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;

    @Override
    public Collection<Customer> getAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer getById(Long id) {
        Optional<Customer> result = customerDao.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public Customer add(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> result = customerDao.findById(customer.getId());
        if (result.isPresent()) {
            return customerDao.save(customer);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> result = customerDao.findById(id);
        if (result.isPresent()) {
            customerDao.delete(result.get());
        }
    }

}

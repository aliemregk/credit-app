package com.credit.app.api.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.entities.concretes.Customer;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/customers")
@AllArgsConstructor
public class CustomersController {

    private CustomerService customerService;

    @GetMapping(path = "getall")
    public Collection<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping(path = "getbyid")
    public Customer getById(@RequestParam Long id) {
        return customerService.getById(id);
    }

    @PostMapping(path = "add")
    public Customer add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @PutMapping(path = "update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping(path = "delete")
    public void delete(@RequestParam Long id) {
        customerService.delete(id);
    }
}

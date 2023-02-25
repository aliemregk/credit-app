package com.credit.app.api.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.responses.customer.GetAllCustomerResponse;
import com.credit.app.business.responses.customer.GetByIdCustomerResponse;
import com.credit.app.core.utilities.results.dataResults.DataResult;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/customers/")
@AllArgsConstructor
public class CustomersController {

    private CustomerService customerService;

    @GetMapping(path = "getall")
    public DataResult<Collection<GetAllCustomerResponse>> getAll() {
        return customerService.getAll();
    }

    @GetMapping(path = "getbyid")
    public DataResult<GetByIdCustomerResponse> getById(@RequestParam Long id) {
        return customerService.getById(id);
    }

}

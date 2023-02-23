package com.credit.app.api.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credit.app.business.abstracts.CustomerService;
import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.business.responses.individualCustomer.GetByIdIndividualCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/customers")
@AllArgsConstructor
public class CustomersController {

    private CustomerService customerService;

    @GetMapping(path = "getall")
    public DataResult<Collection<GetAllIndividualCustomerResponse>> getAll() {
        return customerService.getAll();
    }

    @GetMapping(path = "getbyid")
    public DataResult<GetByIdIndividualCustomerResponse> getById(@RequestParam Long id) {
        return customerService.getById(id);
    }

    @PostMapping(path = "add")
    public Result add(@RequestBody AddIndividualCustomerRequest customer) {
        return customerService.add(customer);
    }

    @PutMapping(path = "update")
    public Result update(@RequestBody UpdateIndividualCustomerRequest customer) {
        return customerService.update(customer);
    }

    @DeleteMapping(path = "delete/{id}")
    public Result delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}

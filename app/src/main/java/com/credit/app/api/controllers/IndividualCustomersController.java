package com.credit.app.api.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.app.business.abstracts.IndividualCustomerService;
import com.credit.app.business.requests.individualCustomer.AddIndividualCustomerRequest;
import com.credit.app.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/individualcustomers/")
@AllArgsConstructor
public class IndividualCustomersController {

    private IndividualCustomerService customerService;

    @GetMapping(path = "getall")
    public DataResult<Collection<GetAllIndividualCustomerResponse>> getAll() {
        return customerService.getAll();
    }

    @PostMapping(path = "add")
    public Result add(@RequestBody @Valid AddIndividualCustomerRequest addRequest) {
        return customerService.add(addRequest);
    }

    @PutMapping(path = "update")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest customer) {
        return customerService.update(customer);
    }

    @DeleteMapping(path = "delete/{id}")
    public Result delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}

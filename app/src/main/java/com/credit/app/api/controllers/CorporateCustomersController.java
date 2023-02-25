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

import com.credit.app.business.abstracts.CorporateCustomerService;
import com.credit.app.business.requests.corporateCustomer.AddCorporateCustomerRequest;
import com.credit.app.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.credit.app.business.responses.corporateCustomer.GetAllCorporateCustomerResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/corporatecustomers/")
@AllArgsConstructor
public class CorporateCustomersController {

    private CorporateCustomerService customerService;

    @GetMapping(path = "getall")
    public DataResult<Collection<GetAllCorporateCustomerResponse>> getAll() {
        return customerService.getAll();
    }

    @PostMapping(path = "add")
    public Result add(@RequestBody AddCorporateCustomerRequest addRequest) {
        return customerService.add(addRequest);
    }

    @PutMapping(path = "update")
    public Result update(@RequestBody UpdateCorporateCustomerRequest updateRequest) {
        return customerService.update(updateRequest);
    }

    @DeleteMapping(path = "delete/{id}")
    public Result delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}

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

import com.credit.app.business.abstracts.CreditService;
import com.credit.app.business.requests.credit.AddCreditForCorporateRequest;
import com.credit.app.business.requests.credit.AddCreditForIndividualRequest;
import com.credit.app.business.responses.credit.CreditResultResponse;
import com.credit.app.business.responses.credit.GetAllCreditResponse;
import com.credit.app.business.responses.credit.GetByIdCreditResponse;
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.Credit;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/credits/")
@AllArgsConstructor
public class CreditsController {

    private CreditService creditService;

    @GetMapping(path = "getall")
    public DataResult<Collection<GetAllCreditResponse>> getAll() {
        return creditService.getAll();
    }

    @GetMapping(path = "getbyid")
    public DataResult<GetByIdCreditResponse> getById(@RequestParam Long id) {
        return creditService.getById(id);
    }

    @PostMapping(path = "addindividual")
    public DataResult<CreditResultResponse> addCreditForIndividual(
            @RequestBody @Valid AddCreditForIndividualRequest addCreditRequest) {
        return creditService.addCreditForIndividual(addCreditRequest);
    }

    @PostMapping(path = "addcorporate")
    public DataResult<CreditResultResponse> addCreditForCorporate(
            @RequestBody @Valid AddCreditForCorporateRequest addCreditRequest) {
        return creditService.addCreditForCorporate(addCreditRequest);
    }

    @PutMapping(path = "update")
    public Result update(@RequestBody @Valid Credit credit) {
        return creditService.update(credit);
    }

    @DeleteMapping(path = "delete")
    public Result delete(@RequestParam Long id) {
        return creditService.delete(id);
    }
}

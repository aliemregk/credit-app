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
import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.dataResults.DataResult;
import com.credit.app.entities.concretes.Credit;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/credits/")
@AllArgsConstructor
public class CreditsController {

    private CreditService creditService;

    @GetMapping(path = "getall")
    public DataResult<Collection<Credit>> getAll() {
        return creditService.getAll();
    }

    @GetMapping(path = "getbyid")
    public DataResult<Credit> getById(@RequestParam Long id) {
        return creditService.getById(id);
    }

    @PostMapping(path = "add")
    public DataResult<Credit> add(@RequestParam String nationalId) {
        return creditService.add(nationalId);
    }

    @PutMapping(path = "update")
    public DataResult<Credit> update(@RequestBody Credit credit) {
        return creditService.update(credit);
    }

    @DeleteMapping(path = "delete")
    public Result delete(@RequestParam Long id) {
        return creditService.delete(id);
    }
}

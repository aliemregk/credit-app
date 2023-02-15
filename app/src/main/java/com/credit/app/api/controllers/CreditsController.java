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
import com.credit.app.entities.concretes.Credit;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/credits/")
@AllArgsConstructor
public class CreditsController {

    private CreditService creditService;

    @GetMapping(path = "getall")
    public Collection<Credit> getAll() {
        return creditService.getAll();
    }

    @GetMapping(path = "getbyid")
    public Credit getById(@RequestParam Long id) {
        return creditService.getById(id);
    }

    @PostMapping(path = "add")
    public Credit add(@RequestBody Credit credit) {
        return creditService.add(credit);
    }

    @PutMapping(path = "update")
    public Credit update(@RequestBody Credit credit) {
        return creditService.update(credit);
    }

    @DeleteMapping(path = "delete")
    public void delete(@RequestParam Long id) {
        creditService.delete(id);
    }
}

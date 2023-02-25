package com.credit.app.business.responses.credit;

import com.credit.app.entities.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCreditResponse {
    private int id;
    private double amount;
    private Customer customer;
}

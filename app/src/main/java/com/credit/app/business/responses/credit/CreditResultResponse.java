package com.credit.app.business.responses.credit;

import com.credit.app.business.responses.individualCustomer.GetByIdIndividualCustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResultResponse {
    private int id;
    private double amount;
    private GetByIdIndividualCustomerResponse customer;
}

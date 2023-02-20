package com.credit.app.business.responses.credit;

import com.credit.app.business.responses.individualCustomer.GetAllIndividualCustomerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCreditResponse {
    private int id;
    private double amount;
    private GetAllIndividualCustomerResponse customer;
}

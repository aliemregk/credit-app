package com.credit.app.business.responses.individualCustomer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllIndividualCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalId;
    private double income;
    private String phone;
    private LocalDate birthDate;
    private double guarantee;
}

package com.credit.app.business.responses.customer;

import java.time.LocalDate;

import com.credit.app.business.constants.CustomerTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class GetAllCustomerResponse {
    private int id;
    private String phone;
    private double guarantee;
    private CustomerTypeEnum customerType;
    private String companyName;
    private String taxNumber;
    private String firstName;
    private String lastName;
    private String nationalId;
    private double income;
    private LocalDate birthDate;
}

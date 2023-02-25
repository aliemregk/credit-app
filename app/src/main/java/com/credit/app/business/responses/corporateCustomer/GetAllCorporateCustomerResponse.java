package com.credit.app.business.responses.corporateCustomer;

import com.credit.app.business.constants.CustomerTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCorporateCustomerResponse {
    private int id;
    private String companyName;
    private String taxNumber;
    private String phone;
    private double guarantee;
    private CustomerTypeEnum customerType;
}

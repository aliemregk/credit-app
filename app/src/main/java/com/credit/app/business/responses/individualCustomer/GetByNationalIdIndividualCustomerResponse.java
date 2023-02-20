package com.credit.app.business.responses.individualCustomer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByNationalIdIndividualCustomerResponse {
    private int id;
    private String nationalId;
    private LocalDate birthDate;
}

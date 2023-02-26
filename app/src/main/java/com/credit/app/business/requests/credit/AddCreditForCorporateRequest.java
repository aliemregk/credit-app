package com.credit.app.business.requests.credit;

import org.hibernate.validator.constraints.Length;

import com.credit.app.business.constants.Messages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCreditForCorporateRequest {

    @NotBlank(message = "Tax number" + Messages.REQUIRED)
    private String taxNumber;

    @NotBlank(message = "Company name" + Messages.REQUIRED)
    @Length(min = 2, message = "Company name" + Messages.TOO_SHORT)
    private String companyName;

    @NotBlank(message = "Phone number" + Messages.REQUIRED)
    private String phone;

    @NotBlank(message = "Income" + Messages.REQUIRED)
    @Min(value = 0, message = "Income" + Messages.MIN)
    private double income;

    @Min(value = 0, message = "Guarantee" + Messages.MIN)
    private double guarantee;

}

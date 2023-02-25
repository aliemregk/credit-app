package com.credit.app.business.requests.individualCustomer;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.credit.app.business.constants.CustomerTypeEnum;
import com.credit.app.business.constants.Messages;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIndividualCustomerRequest {
    @NotBlank(message = "First name" + Messages.REQUIRED)
    @Length(min = 2, message = "First name" + Messages.TOO_SHORT)
    private String firstName;

    @NotBlank(message = "Last name" + Messages.REQUIRED)
    private String lastName;

    @NotBlank(message = "National ID" + Messages.REQUIRED)
    private String nationalId;

    @NotBlank(message = "Income" + Messages.REQUIRED)
    @Min(value = 0, message = "Income" + Messages.MIN)
    private double income;

    @NotBlank(message = "Phone number" + Messages.REQUIRED)
    private String phone;

    @NotNull(message = "Birth date" + Messages.REQUIRED)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Min(value = 0, message = "Guarantee" + Messages.MIN)
    private double guarantee;

    @NotBlank(message = "Customer type " + Messages.REQUIRED)
    private CustomerTypeEnum customerType;
}

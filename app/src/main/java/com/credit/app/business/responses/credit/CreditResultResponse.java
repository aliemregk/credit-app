package com.credit.app.business.responses.credit;

import com.credit.app.business.constants.CreditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditResultResponse {
    private double amount;
    private CreditStatusEnum status;
}

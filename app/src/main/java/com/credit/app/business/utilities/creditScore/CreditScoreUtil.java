package com.credit.app.business.utilities.creditScore;

import org.springframework.stereotype.Component;

@Component
public class CreditScoreUtil implements CreditScoreService {

    @Override
    public int getCreditScore(String nationalId) {
        return nationalId.charAt(3) * nationalId.charAt(7) % nationalId.charAt(2) * 100;
    }

}

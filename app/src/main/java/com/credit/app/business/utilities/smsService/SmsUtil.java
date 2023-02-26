package com.credit.app.business.utilities.smsService;

import org.springframework.stereotype.Component;

@Component
public class SmsUtil implements SmsService {

    @Override
    public void sendInformation(String message) {
        System.out.println("Credit approved for customer " + message + " .");
    }

}

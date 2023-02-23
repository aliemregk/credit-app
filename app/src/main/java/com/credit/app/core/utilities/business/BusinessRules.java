package com.credit.app.core.utilities.business;

import com.credit.app.core.utilities.results.Result;
import com.credit.app.core.utilities.results.SuccessResult;

public final class BusinessRules {

    private BusinessRules() {
    }

    public static Result run(Result... rules) {
        for (Result result : rules) {
            if (!result.isSuccess()) {
                return result;
            }
        }
        return new SuccessResult();
    }
}

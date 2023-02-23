package com.credit.app.core.utilities.results;

import lombok.Getter;

@Getter
public class Result {
    private String message;
    private Boolean success;

    public Result(String message, Boolean success) {
        this(success);
        this.message = message;
    }

    public Result(Boolean success) {
        this.success = success;
    }

}

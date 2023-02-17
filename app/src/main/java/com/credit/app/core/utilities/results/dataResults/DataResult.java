package com.credit.app.core.utilities.results.dataResults;

import com.credit.app.core.utilities.results.Result;

import lombok.Getter;

@Getter
public class DataResult<T> extends Result {

    private T data;

    public DataResult(String message, Boolean success, T data) {
        super(message, success);
        this.data = data;
    }

    public DataResult(Boolean success, T data) {
        super(success);
        this.data = data;
    }

}

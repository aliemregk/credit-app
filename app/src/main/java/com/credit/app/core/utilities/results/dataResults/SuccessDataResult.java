package com.credit.app.core.utilities.results.dataResults;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(String message, T data) {
        super(message, true, data);
    }

    public SuccessDataResult(T data) {
        super(true, data);
    }

}

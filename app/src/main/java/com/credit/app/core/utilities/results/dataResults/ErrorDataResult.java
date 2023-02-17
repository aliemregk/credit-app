package com.credit.app.core.utilities.results.dataResults;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(String message, T data) {
        super(message, false, data);
    }

    public ErrorDataResult(T data) {
        super(false, data);
    }

}

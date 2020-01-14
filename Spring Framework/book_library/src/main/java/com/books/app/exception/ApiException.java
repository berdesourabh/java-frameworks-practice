package com.books.app.exception;

public class ApiException extends Exception {

    private final ApiError apiError;

    public ApiException(ApiError error) {
        super();
        this.apiError = error;
    }


    public ApiError getApiError() {
        return apiError;
    }

}

package com.books.app.exception;

public enum ReasonCode {
    INTERNAL_SERVER_ERROR("000"),
    RECORD_NOT_PRESENT_001("001"),
    INVALID_REQUEST_002("002"),
    CREATION_FAILED_003("003");

    private String code;

    ReasonCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}

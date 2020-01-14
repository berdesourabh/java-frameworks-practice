package com.books.app.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonPropertyOrder({"Source", "ReasonCode", "Description"})
public class ApiError implements Serializable {

    @JsonProperty("Source")
    private final String source;

    @JsonProperty("ReasonCode")
    private final String reasonCode;

    @JsonProperty("Description")
    private final String description;

    @JsonProperty
    private final HttpStatus httpStatus;


    public ApiError(String source, String reasonCode, String description, HttpStatus httpStatus) {
        this.source = source;
        this.reasonCode = reasonCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public String getSource() {
        return source;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "source='" + source + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

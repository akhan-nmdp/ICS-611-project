package com.baeldung.spring.reactive.customexception.model;


import com.baeldung.spring.reactive.customexception.config.ErrorDetailsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@JsonSerialize(using = ErrorDetailsSerializer.class)
public enum ErrorDetails {

    API_USER_NOT_FOUND(123, "User not found", "http://example.com/123");
    @Getter
    private final Integer errorCode;
    @Getter
    private final String errorMessage;
    @Getter
    private final String referenceUrl;

    ErrorDetails(Integer errorCode, String errorMessage, String referenceUrl) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.referenceUrl = referenceUrl;
    }
}

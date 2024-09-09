package com.clush.assignment.global.exception;

import org.springframework.http.HttpStatus;

public class ExpectedException extends RuntimeException {

    private final HttpStatus statusCode;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public ExpectedException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}

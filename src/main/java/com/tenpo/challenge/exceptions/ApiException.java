package com.tenpo.challenge.exceptions;

public class ApiException extends RuntimeException {
    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }
}

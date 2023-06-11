package com.tenpo.challenge.exceptions;

public class LimitRequestException extends ApiException {
    public LimitRequestException() {
        super("The api does not permit further calls.");
    }
}

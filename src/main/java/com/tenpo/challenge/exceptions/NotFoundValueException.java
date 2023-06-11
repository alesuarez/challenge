package com.tenpo.challenge.exceptions;

public class NotFoundValueException extends ApiException {
    public NotFoundValueException() {
        super("Cannot provide the value for this operation.");
    }
}

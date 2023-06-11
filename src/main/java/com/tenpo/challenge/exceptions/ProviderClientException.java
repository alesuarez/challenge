package com.tenpo.challenge.exceptions;

public class ProviderClientException extends ApiException {
    public ProviderClientException() {
        super("Provider unable to process.");
    }
}

package com.swiss.feature.toggles.service.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Customer with id: %s not found";

    public CustomerNotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}

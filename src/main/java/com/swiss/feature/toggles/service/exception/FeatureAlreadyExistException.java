package com.swiss.feature.toggles.service.exception;

public class FeatureAlreadyExistException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Feature with technical name: %s already exists";

    public FeatureAlreadyExistException(String name) {
        super(String.format(ERROR_MESSAGE, name));
    }
}

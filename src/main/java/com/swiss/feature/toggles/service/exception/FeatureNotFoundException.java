package com.swiss.feature.toggles.service.exception;

public class FeatureNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Non-existent feature passed";

    public FeatureNotFoundException() {
        super(ERROR_MESSAGE);
    }
}

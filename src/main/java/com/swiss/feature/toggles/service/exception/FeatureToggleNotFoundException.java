package com.swiss.feature.toggles.service.exception;

public class FeatureToggleNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Feature toggle with id: %s not found";

    public FeatureToggleNotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}

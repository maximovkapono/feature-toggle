package com.swiss.feature.toggles.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FeatureRequestWrapper {

    @NotNull
    private FeatureRequest featureRequest;
}

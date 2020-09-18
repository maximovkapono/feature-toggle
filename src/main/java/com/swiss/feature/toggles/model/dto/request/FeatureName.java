package com.swiss.feature.toggles.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FeatureName {

    @NotNull
    private String name;
}

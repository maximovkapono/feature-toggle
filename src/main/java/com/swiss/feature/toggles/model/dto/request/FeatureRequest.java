package com.swiss.feature.toggles.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class FeatureRequest {

    @NotNull
    private Long customerId;

    @NotEmpty
    private List<FeatureName> features;
}

package com.swiss.feature.toggles.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureInfo {

    private String name;

    private Boolean active;

    private Boolean inverted;

    private Boolean expired;
}

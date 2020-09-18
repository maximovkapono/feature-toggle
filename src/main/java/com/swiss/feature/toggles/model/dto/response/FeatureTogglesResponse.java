package com.swiss.feature.toggles.model.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swiss.feature.toggles.model.entity.CustomerEntity;
import com.swiss.feature.toggles.model.entity.FeatureEntity;
import com.swiss.feature.toggles.model.entity.FeatureToggleEntity;
import com.swiss.feature.toggles.service.serializer.LocalDateTimeToStringSerializer;
import com.swiss.feature.toggles.service.serializer.NumberCollectionToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FeatureTogglesResponse {

    private Long id;

    private String displayName;

    private String technicalName;

    private String description;

    @JsonSerialize(using = LocalDateTimeToStringSerializer.class)
    private LocalDateTime expire;

    private Boolean inverted;

    @JsonSerialize(using = NumberCollectionToString.class)
    private Set<Long> customerIds;

    public FeatureTogglesResponse(FeatureToggleEntity featureToggle) {
        FeatureEntity feature = featureToggle.getFeature();
        this.id = featureToggle.getId();
        this.displayName = feature.getDisplayName();
        this.technicalName = feature.getTechnicalName();
        this.description = feature.getDescription();
        this.expire = featureToggle.getExpire();
        this.inverted = featureToggle.getInverted();
        this.customerIds = featureToggle.getCustomers().stream()
                .map(CustomerEntity::getId)
                .collect(Collectors.toSet());
    }
}

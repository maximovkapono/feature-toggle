package com.swiss.feature.toggles.model.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.swiss.feature.toggles.service.deserializer.StringToLocalDateTimeDeserializer;
import com.swiss.feature.toggles.service.deserializer.StringToNumberCollection;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class FeatureCreateRequest {

    private String displayName;

    @NotNull
    private String technicalName;

    private String description;

    @JsonDeserialize(using = StringToLocalDateTimeDeserializer.class)
    private LocalDateTime expire;

    @NotNull
    private Boolean inverted;

    @NotEmpty
    @JsonDeserialize(using = StringToNumberCollection.class)
    private Set<Long> customerIds;
}

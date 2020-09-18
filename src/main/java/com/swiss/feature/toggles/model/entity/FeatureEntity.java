package com.swiss.feature.toggles.model.entity;

import com.swiss.feature.toggles.model.dto.request.FeatureCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "feature")
public class FeatureEntity extends CreateAndUpdateBaseEntity {

    private static final String FEATURE_SEQ = "feature_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FEATURE_SEQ)
    @SequenceGenerator(name = FEATURE_SEQ, sequenceName = FEATURE_SEQ, allocationSize = 10)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "technical_name")
    private String technicalName;

    @Column(name = "description")
    private String description;

    public FeatureEntity(FeatureCreateRequest request) {
        setFields(request);
    }

    public void update(FeatureCreateRequest request) {
        setFields(request);
    }

    private void setFields(FeatureCreateRequest request) {
        this.displayName = request.getDisplayName();
        this.technicalName = request.getTechnicalName();
        this.description = request.getDescription();
    }
}

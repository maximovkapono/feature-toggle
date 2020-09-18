package com.swiss.feature.toggles.model.entity;

import com.swiss.feature.toggles.model.dto.request.FeatureCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "feature_toggle")
public class FeatureToggleEntity extends CreateAndUpdateBaseEntity {

    private static final String FEATURE_TOGGLE_SEQ = "feature_toggle_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FEATURE_TOGGLE_SEQ)
    @SequenceGenerator(name = FEATURE_TOGGLE_SEQ, sequenceName = FEATURE_TOGGLE_SEQ, allocationSize = 10)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private FeatureEntity feature;

    @Column(name = "expire")
    private LocalDateTime expire;

    @Column(name = "inverted")
    private Boolean inverted;

    @ManyToMany
    @JoinTable(name = "feature_toggle_customer",
            joinColumns = @JoinColumn(name = "feature_toggle_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<CustomerEntity> customers;

    public FeatureToggleEntity(FeatureCreateRequest request, FeatureEntity feature, List<CustomerEntity> customers) {
        this.feature = feature;
        this.customers = customers;
        updateFields(request);
    }

    public void updateFields(FeatureCreateRequest request) {
        this.expire = request.getExpire();
        this.inverted = request.getInverted();
    }
}

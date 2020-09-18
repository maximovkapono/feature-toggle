package com.swiss.feature.toggles.model.repository;

import com.swiss.feature.toggles.model.entity.FeatureToggleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureToggleRepository extends JpaRepository<FeatureToggleEntity, Long> {
    List<FeatureToggleEntity> findAllByFeatureTechnicalNameIn(List<String> names);

    FeatureToggleEntity findByFeatureTechnicalName(String name);
}

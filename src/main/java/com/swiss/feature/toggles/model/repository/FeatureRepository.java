package com.swiss.feature.toggles.model.repository;

import com.swiss.feature.toggles.model.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
    Optional<FeatureEntity> findByTechnicalName(String technicalName);
}

package com.swiss.feature.toggles.model.repository;

import com.swiss.feature.toggles.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}

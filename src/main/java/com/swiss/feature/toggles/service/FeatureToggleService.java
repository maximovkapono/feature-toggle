package com.swiss.feature.toggles.service;

import com.swiss.feature.toggles.model.dto.request.FeatureArchiveRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureCreateRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureUpdateRequest;
import com.swiss.feature.toggles.model.dto.response.FeatureTogglesResponse;
import com.swiss.feature.toggles.model.entity.CustomerEntity;
import com.swiss.feature.toggles.model.entity.FeatureEntity;
import com.swiss.feature.toggles.model.entity.FeatureToggleEntity;
import com.swiss.feature.toggles.model.repository.CustomerRepository;
import com.swiss.feature.toggles.model.repository.FeatureRepository;
import com.swiss.feature.toggles.model.repository.FeatureToggleRepository;
import com.swiss.feature.toggles.service.exception.FeatureAlreadyExistException;
import com.swiss.feature.toggles.service.exception.FeatureToggleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {

    private final CustomerRepository customerRepository;
    private final FeatureToggleRepository featureToggleRepository;
    private final FeatureRepository featureRepository;

    @Transactional
    public List<FeatureTogglesResponse> getFeatureToggles() {
        return featureToggleRepository.findAll().stream()
                .map(FeatureTogglesResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(FeatureCreateRequest request) {
        if (featureRepository.findByTechnicalName(request.getTechnicalName()).isPresent()) {
            throw new FeatureAlreadyExistException(request.getTechnicalName());
        }
        FeatureEntity feature = new FeatureEntity(request);
        featureRepository.save(feature);
        List<CustomerEntity> customers = getCustomers(request);
        customerRepository.saveAll(customers);
        FeatureToggleEntity featureToggle = new FeatureToggleEntity(request, feature, customers);
        featureToggleRepository.save(featureToggle);
    }

    @Transactional
    public void update(FeatureUpdateRequest request) {
        FeatureToggleEntity featureToggle = getFeatureToggleOrThrowException(request.getId());
        featureToggle.getFeature().update(request);
        List<CustomerEntity> customers = getCustomers(request);
        customerRepository.saveAll(customers);
        customerRepository.flush();
        featureToggle.setCustomers(customers);
        featureToggle.updateFields(request);
    }

    @Transactional
    public void archive(FeatureArchiveRequest request) {
        FeatureToggleEntity featureToggle = getFeatureToggleOrThrowException(request.getId());
        featureToggle.setExpire(LocalDateTime.now());
    }

    private FeatureToggleEntity getFeatureToggleOrThrowException(Long id) {
        return featureToggleRepository.findById(id)
                .orElseThrow(() -> new FeatureToggleNotFoundException(id));
    }

    private List<CustomerEntity> getCustomers(FeatureCreateRequest request) {
        return request.getCustomerIds().stream()
                .map(id -> customerRepository.findById(id).orElse(new CustomerEntity(id)))
                .collect(Collectors.toList());
    }
}

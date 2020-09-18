package com.swiss.feature.toggles.service;

import com.swiss.feature.toggles.model.dto.request.FeatureName;
import com.swiss.feature.toggles.model.dto.request.FeatureRequest;
import com.swiss.feature.toggles.model.dto.response.FeatureInfo;
import com.swiss.feature.toggles.model.dto.response.FeatureResponse;
import com.swiss.feature.toggles.model.entity.CustomerEntity;
import com.swiss.feature.toggles.model.entity.FeatureToggleEntity;
import com.swiss.feature.toggles.model.repository.CustomerRepository;
import com.swiss.feature.toggles.model.repository.FeatureToggleRepository;
import com.swiss.feature.toggles.service.exception.CustomerNotFoundException;
import com.swiss.feature.toggles.service.exception.FeatureNotFoundException;
import com.swiss.feature.toggles.service.logic.Active;
import com.swiss.feature.toggles.service.logic.Expired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final CustomerRepository customerRepository;
    private final FeatureToggleRepository featureToggleRepository;

    @Transactional
    public FeatureResponse features(FeatureRequest request) {
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        List<String> names = request.getFeatures().stream()
                .map(FeatureName::getName)
                .collect(Collectors.toList());
        List<FeatureToggleEntity> features = featureToggleRepository.findAllByFeatureTechnicalNameIn(names);
        if (features.size() < names.size()) {
            throw new FeatureNotFoundException();
        }
        List<FeatureInfo> featureInfos = features.stream()
                .map(featureToggle -> getFeatureInfo(customer, featureToggle))
                .collect(Collectors.toList());
        return new FeatureResponse(featureInfos);
    }

    private FeatureInfo getFeatureInfo(CustomerEntity customer, FeatureToggleEntity featureToggle) {
        boolean inverted = featureToggle.getInverted();
        boolean expired = new Expired(featureToggle.getExpire()).get();
        boolean customerInList = featureToggle.getCustomers().contains(customer);
        String technicalName = featureToggle.getFeature().getTechnicalName();
        return new FeatureInfo(technicalName, new Active(expired, inverted, customerInList).get(), inverted, expired);
    }
}

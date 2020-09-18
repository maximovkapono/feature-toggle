package com.swiss.feature.toggles.service;

import com.swiss.feature.toggles.model.dto.request.FeatureArchiveRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureCreateRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureName;
import com.swiss.feature.toggles.model.dto.request.FeatureRequest;
import com.swiss.feature.toggles.model.dto.response.FeatureResponse;
import com.swiss.feature.toggles.model.entity.FeatureToggleEntity;
import com.swiss.feature.toggles.model.repository.FeatureToggleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeatureServiceTest {

    private static final String TECHNICAL_NAME_1 = "name1";
    private static final String TECHNICAL_NAME_2 = "name2";

    @Autowired
    private FeatureToggleService featureToggleService;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @BeforeAll
    public void setup() {
        FeatureCreateRequest createRequest1 = new FeatureCreateRequest();
        createRequest1.setTechnicalName(TECHNICAL_NAME_1);
        createRequest1.setInverted(false);
        createRequest1.setCustomerIds(new HashSet<>(Arrays.asList(1L, 2L, 3L)));
        FeatureCreateRequest createRequest2 = new FeatureCreateRequest();
        createRequest2.setTechnicalName(TECHNICAL_NAME_2);
        createRequest2.setInverted(true);
        createRequest2.setExpire(LocalDateTime.now().plusYears(1));
        createRequest2.setCustomerIds(new HashSet<>(Arrays.asList(1L, 2L)));
        featureToggleService.create(createRequest1);
        featureToggleService.create(createRequest2);
    }

    @Test
    @Transactional
    void testFeatureCreation() {
        List<FeatureToggleEntity> features = featureToggleRepository
                .findAllByFeatureTechnicalNameIn(Arrays.asList(TECHNICAL_NAME_1, TECHNICAL_NAME_2));
        assertEquals(2, features.size());
        FeatureToggleEntity feature = features.stream()
                .filter(featureToggle -> TECHNICAL_NAME_1.equals(featureToggle.getFeature().getTechnicalName()))
                .findFirst()
                .orElseThrow();
        assertEquals(3, feature.getCustomers().size());
    }

    @Test
    @Transactional
    void testFeatureArchive() {
        FeatureToggleEntity feature = featureToggleRepository.findByFeatureTechnicalName(TECHNICAL_NAME_1);
        assertNull(feature.getExpire());
        FeatureArchiveRequest request = new FeatureArchiveRequest();
        request.setId(feature.getId());
        featureToggleService.archive(request);
        FeatureToggleEntity updatedFeature = featureToggleRepository.findById(feature.getId())
                .orElseThrow();
        assertNotNull(updatedFeature.getExpire());
        feature.setExpire(null);
    }

    @Test
    void testFeatureAPI() {
        FeatureRequest request = new FeatureRequest();
        request.setCustomerId(3L);
        FeatureName featureName1 = new FeatureName();
        featureName1.setName(TECHNICAL_NAME_1);
        FeatureName featureName2 = new FeatureName();
        featureName2.setName(TECHNICAL_NAME_2);
        request.setFeatures(Arrays.asList(featureName1, featureName2));
        FeatureResponse features = featureService.features(request);
        features.getFeatures().forEach(featureInfo -> {
            if (TECHNICAL_NAME_1.equals(featureInfo.getName())) {
                assertTrue(featureInfo.getActive());
                assertFalse(featureInfo.getExpired());
                assertFalse(featureInfo.getInverted());
            }
            if (TECHNICAL_NAME_2.equals(featureInfo.getName())) {
                assertFalse(featureInfo.getActive());
                assertFalse(featureInfo.getExpired());
                assertTrue(featureInfo.getInverted());
            }
        });
    }
}
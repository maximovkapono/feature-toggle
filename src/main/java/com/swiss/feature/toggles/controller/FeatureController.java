package com.swiss.feature.toggles.controller;

import com.swiss.feature.toggles.model.dto.request.FeatureRequestWrapper;
import com.swiss.feature.toggles.model.dto.response.FeatureResponse;
import com.swiss.feature.toggles.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FeatureController {

    private final FeatureService featureService;

    @PostMapping("/features")
    public FeatureResponse features(@RequestBody @Valid FeatureRequestWrapper request) {
        return featureService.features(request.getFeatureRequest());
    }
}

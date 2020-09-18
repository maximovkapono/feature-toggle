package com.swiss.feature.toggles.controller;

import com.swiss.feature.toggles.model.dto.request.FeatureArchiveRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureCreateRequest;
import com.swiss.feature.toggles.model.dto.request.FeatureUpdateRequest;
import com.swiss.feature.toggles.model.dto.response.FeatureTogglesResponse;
import com.swiss.feature.toggles.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/feature_toggle")
public class FeatureToggleController {

    private final FeatureToggleService featureToggleService;

    @GetMapping
    public List<FeatureTogglesResponse> getFeatureToggles() {
        return featureToggleService.getFeatureToggles();
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid FeatureCreateRequest request) {
        featureToggleService.create(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid FeatureUpdateRequest request) {
        featureToggleService.update(request);
    }

    @PostMapping("/archive")
    public void archive(@RequestBody @Valid FeatureArchiveRequest request) {
        featureToggleService.archive(request);
    }
}

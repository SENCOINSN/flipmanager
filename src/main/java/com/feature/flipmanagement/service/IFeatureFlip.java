package com.feature.flipmanagement.service;

import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;

import java.util.List;

public interface IFeatureFlip {
    List<FeatureDTO> listAllFeatures();

    FeatureDTO getFeatureByUuid(String uuid);

    FeatureDTO createFeature(FeatureRequest request);

    FeatureDTO updateFeature(FeatureRequest request);

    void deleteFeature(String uuid);

    boolean changeStatus(String uuid);


}

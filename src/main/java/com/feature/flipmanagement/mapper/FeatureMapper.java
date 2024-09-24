package com.feature.flipmanagement.mapper;

import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.model.FeatureFlip;

public class FeatureMapper {

    public static FeatureDTO featureToFeatureDTO(FeatureFlip feature) {
        return new FeatureDTO(
                feature.getUuid(), feature.getNameFeature(), feature.isActivate(), feature.getCreateAt());

    }

    public static FeatureFlip featureRequestToFeature(FeatureRequest request) {
        FeatureFlip featureFlip = new FeatureFlip();
        if(request!=null){
            featureFlip.setNameFeature(request.getNameFeature());
            featureFlip.setDescriptionFeature(request.getDescriptionFeature());
            return featureFlip;
        }
       return featureFlip;
    }

}

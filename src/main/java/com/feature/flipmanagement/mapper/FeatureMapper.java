package com.feature.flipmanagement.mapper;

import com.feature.flipmanagement.dto.FeatureContextDTO;
import com.feature.flipmanagement.dto.FeatureContextRequest;
import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.model.FeatureContext;
import com.feature.flipmanagement.model.FeatureFlip;

public class FeatureMapper {

    public static FeatureDTO featureToFeatureDTO(FeatureFlip feature) {
        return new FeatureDTO(
                feature.getUuid(), feature.getNameFeature(), feature.isActivate(), feature.getDescriptionFeature(),feature.getCreateAt());

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

    public static FeatureContext featureRequestToFeatureContext(FeatureContextRequest request) {
        FeatureContext featureContext = new FeatureContext();
        if(request!=null){
            featureContext.setNameFeature(request.getNameFeature());
            if(request.getTargetUser()!=null){
                featureContext.setTargetUser(request.getTargetUser());
            }

            if(request.getTargetGroup()!=null){
                featureContext.setTargetGroup(request.getTargetGroup());
            }

            return featureContext;
        }
       return featureContext;
    }

    public static FeatureContextDTO featureContextToFeatureContextDTO(FeatureContext featureContext) {
        return new FeatureContextDTO(featureContext.getUuid(),
                featureContext.getNameFeature(),
                featureContext.getTargetUser(),
                featureContext.getTargetGroup(),
                featureContext.getUserGroups(),
                featureContext.getCreateAt());
    }



}

package com.feature.flipmanagement.dto;

import java.util.Date;

public record FeatureDTO(String uuid,
                         String nameFeature,
                         boolean activate,
                         Date createAt) {
}

package com.feature.flipmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FeatureRequest(
        @NotNull(message = "nameFeature is required")
                @NotBlank(message = "nameFeature must not be blank")
    String nameFeature,
    String descriptionFeature
) {
}

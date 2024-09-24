package com.feature.flipmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureRequest{
    private String uuid;
    @NotNull(message = "nameFeature is required")
    @NotBlank(message = "nameFeature must not be blank")
    private String nameFeature;
    private String descriptionFeature;
}

package com.feature.flipmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureContextRequest {
 private String uuid;
 @NotNull(message = "nameFeature is required")
 @NotBlank(message = "nameFeature must not be blank")
 private String nameFeature;
 private String targetUser;
 private String targetGroup;
 private List<String> userGroups = new ArrayList<>();

}

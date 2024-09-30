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
public class LoginRequest {
    @NotNull(message = "username is required")
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotNull(message = "password is required")
    @NotBlank(message = "password must not be blank")
    private String password;
}

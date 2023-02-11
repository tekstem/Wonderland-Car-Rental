package com.edu.miu.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@Setter
@Getter
@NoArgsConstructor
public class AuthDTO {
    @NotEmpty(message = "password is required")
    private String username;
    @NotEmpty(message = "username is required")
    private String password;
}

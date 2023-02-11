package com.edu.miu.backend.dto;

import com.edu.miu.backend.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank(message = "username is required")
    private String username;

    @Column(unique = true)
    private String driverLicenseNumber;

    @Column(unique = true)
    private String contactPhoneNumber;

    @NotNull
    private Role role;
}

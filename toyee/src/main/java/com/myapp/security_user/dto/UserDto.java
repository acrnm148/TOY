package com.myapp.security_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message = "Enter your fistName")
    private String firstName;

    @NotEmpty(message = "Enter your lastName")
    private String lastName;

    @NotEmpty(message = "Enter your email")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Enter your password")
    private String password;

    @NotEmpty(message = "Enter your username")
    private String username;

    @NotEmpty(message = "Confirm your password")
    private String confirmPassword;
}

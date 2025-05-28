package com.project.adaptationflow.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank(message = "Login is mandatory")
    @Size(min = 3, max = 100)
    private String login;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 100)
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
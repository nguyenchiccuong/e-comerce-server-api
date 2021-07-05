package com.rookies.ecommerceapi.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{7,15}", message = "Invalid username!")
    private String username;
    // star with character, have ., number, charracter length from 7->15

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$", message = "Invalid password!")
    private String password;
    // Minimum eight and maximum 10 characters, at least one uppercase letter, one
    // lowercase letter, one number and one special character

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

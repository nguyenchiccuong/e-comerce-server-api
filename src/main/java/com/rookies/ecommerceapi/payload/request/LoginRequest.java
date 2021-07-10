package com.rookies.ecommerceapi.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{7,15}", message = "Invalid username!")
    private String username;
    // star with character, have ., number, charracter length from 8->16

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$", message = "Invalid password!")
    private String password;
    // Minimum eight and maximum 10 characters, at least one uppercase letter, one
    // lowercase letter, one number and one special character

}

package com.dop.resapi.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private String password;

    public LoginResponse(String email, String pass){
        this.email = email;
        this.password = pass;
    }
}

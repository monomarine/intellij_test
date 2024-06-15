package com.dop.resapi.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return  this.password;
    }
}
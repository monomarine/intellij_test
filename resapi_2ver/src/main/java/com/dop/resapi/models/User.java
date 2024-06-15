package com.dop.resapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String name, String pass){
        this.email=email;
        this.name = name;
        this.password=pass;
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return  this.password;
    }
}

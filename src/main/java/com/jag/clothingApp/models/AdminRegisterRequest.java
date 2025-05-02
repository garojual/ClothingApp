package com.jag.clothingApp.models;

import lombok.Getter;

@Getter
public class AdminRegisterRequest {
    private String email;
    private String username;
    private String password;
    private Roles role;
}

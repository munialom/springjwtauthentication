package com.ctecx.springjwtauthentication.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDto {
    private String username;
    private  String password;
    private Set<String> role;
}

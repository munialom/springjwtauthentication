package com.ctecx.springjwtauthentication.dtos;

import lombok.Data;

import javax.persistence.SecondaryTable;

@Data
public class AuthResposeDto {
    private String accessToken;
    private String  tokenType="Bearer";

    public  AuthResposeDto(String accessToken){
        this.accessToken=accessToken;
    }
}

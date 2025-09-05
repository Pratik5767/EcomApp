package com.project.ecom.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;
    private String password;
}

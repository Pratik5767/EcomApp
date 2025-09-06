package com.project.ecom.services.auth;

import com.project.ecom.dtos.SignupRequest;
import com.project.ecom.dtos.UserDto;

public interface IAuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
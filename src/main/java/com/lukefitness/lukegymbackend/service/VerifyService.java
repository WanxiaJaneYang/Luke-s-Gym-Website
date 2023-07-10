package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.response.register.UserResponse;

import java.util.Map;

public interface VerifyService {
    public UserResponse verifyEmail(int tokenId, String token);
    public UserResponse verifyResetPw(int tokenId, String token);
}

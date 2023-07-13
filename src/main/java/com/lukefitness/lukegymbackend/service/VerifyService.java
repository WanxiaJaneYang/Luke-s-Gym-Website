package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.dto.response.register.UserResponse;


public interface VerifyService {
    UserResponse verifyEmail(int tokenId, String token);
    UserResponse verifyResetPw(int tokenId, String token);
}

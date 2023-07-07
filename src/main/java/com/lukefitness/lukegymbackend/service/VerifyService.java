package com.lukefitness.lukegymbackend.service;

import java.util.Map;

public interface VerifyService {
    public void verifyEmail(int tokenId, String token);
    public Map<String, Object> verifyResetPw(int tokenId, String token);
}

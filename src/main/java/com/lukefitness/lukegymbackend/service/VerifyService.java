package com.lukefitness.lukegymbackend.service;

import java.util.Map;

public interface VerifyService {
    public Map<String, Object> verifyEmail(int tokenId, String token);
    public Map<String, Object> verifyResetPw(int tokenId, String token);
}

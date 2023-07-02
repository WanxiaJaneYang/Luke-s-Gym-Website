package com.lukefitness.lukegymbackend.utils;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTUtils {
    private static String secret;
    private static Integer expirationTime;

    public void setSecret(String secret) {
        JWTUtils.secret = secret;
    }

    public void setExpirationTime(Integer expirationTime) {
        JWTUtils.expirationTime = expirationTime;
    }

    public static String getToken(String userId,String userName, String userType) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationTime);

        JWTCreator.Builder builder= com.auth0.jwt.JWT.create();
        Map<String,String> payload =new HashMap<>();
        payload.put("userId",userId);
        payload.put("userName",userName);
        payload.put("userRole",userType);
        payload.forEach(builder::withClaim);
        return builder.withExpiresAt(calendar.getTime()).sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(secret));
    }

    public static DecodedJWT verifyToken(String token) {
        return com.auth0.jwt.JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(secret)).build().verify(token);
    }
}

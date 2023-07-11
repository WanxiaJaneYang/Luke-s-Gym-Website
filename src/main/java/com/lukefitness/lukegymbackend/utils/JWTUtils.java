package com.lukefitness.lukegymbackend.utils;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.User;
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

    public static boolean isTokenExpired(DecodedJWT decodedToken) {
        return decodedToken.getExpiresAt().before(Calendar.getInstance().getTime());
    }

    public void setSecret(String secret) {
        JWTUtils.secret = secret;
    }

    public void setExpirationTime(Integer expirationTime) {
        JWTUtils.expirationTime = expirationTime;
    }

    public static String getToken(String user_id,String username, String user_type, boolean email_verified, boolean is_active) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationTime);

        JWTCreator.Builder builder= com.auth0.jwt.JWT.create();
        Map<String,String> payload =new HashMap<>();
        payload.put("userId",user_id);
        payload.put("userName",username);
        payload.put("userType",user_type);
        payload.put("emailVerified",String.valueOf(email_verified));
        payload.put("isActive",String.valueOf(is_active));
        payload.forEach(builder::withClaim);
        return builder.withExpiresAt(calendar.getTime()).sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(secret));
    }

    public static String getToken(User user){
        if (user instanceof Trainer) {
            if (((Trainer) user).is_admin()) {
                return getToken(String.valueOf(user.getId()), user.getUsername(), "admin", user.isEmail_verified(), user.is_active());
            }
            return getToken(String.valueOf(user.getId()), user.getUsername(), "trainer", user.isEmail_verified(), user.is_active());
        } else if (user instanceof Trainee) {
            return getToken(String.valueOf(user.getId()), user.getUsername(), "trainee", user.isEmail_verified(), user.is_active());
        } else {
            throw new RuntimeException("Invalid user type");
        }
    }

    public static DecodedJWT decodeToken(String token) {
        return com.auth0.jwt.JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(secret)).build().verify(token);
    }

    public static void validateToken(DecodedJWT decodedToken) {
        validateTokenForVerifyEmail(decodedToken);

        //Check if the email is verified
        if (!"true".equals(decodedToken.getClaim("emailVerified").asString())) {
            throw new UnauthorizedException("Email is not verified");
        }
    }

    public static void validateTokenForVerifyEmail(DecodedJWT decodedToken) {
        // Check if token is expired
        if (isTokenExpired(decodedToken)) {
            throw new UnauthorizedException("Token is expired");
        }

        // Check if account is not active
        if (!"true".equals(decodedToken.getClaim("isActive").asString())) {
            throw new UnauthorizedException("Account is not active");
        }
    }
}

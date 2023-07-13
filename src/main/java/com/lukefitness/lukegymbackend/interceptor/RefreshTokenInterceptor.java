package com.lukefitness.lukegymbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.RoleAndIdValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //allow the preflight request
        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        //get the token from the header
        String token;
        try{
            token=request.getHeader("Authorization").substring(7);
        }catch(Exception e){
            throw new BadRequestException("Token is null");
        }

        //check the role and id
        String url=request.getRequestURI();
        DecodedJWT decodedToken = JWTUtils.decodeRefreshToken(token);
        RoleAndIdValidator.validate(url, decodedToken);

        //check the validity of the token
        JWTUtils.validateToken(decodedToken);
        return true;
    }
}

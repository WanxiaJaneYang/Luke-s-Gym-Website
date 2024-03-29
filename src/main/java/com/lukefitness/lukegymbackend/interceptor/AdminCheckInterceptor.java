package com.lukefitness.lukegymbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token;
        //check the token first
        try{
            token=request.getHeader("Authorization").substring(7);
        }catch(Exception e){
            throw new BadRequestException("Token is null");
        }

        DecodedJWT decodedToken = JWTUtils.decodeToken(token);
        if(!decodedToken.getClaim("userType").asString().equals("admin")){
            throw new UnauthorizedException("User is not a trainer");
        }

        //check the validity of the token
        JWTUtils.validateToken(decodedToken);

        return true;
    }
}

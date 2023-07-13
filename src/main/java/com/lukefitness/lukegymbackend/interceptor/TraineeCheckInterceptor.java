package com.lukefitness.lukegymbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.UrlIdExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TraineeCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //check the token first
        String token;
        try {
            token=request.getHeader("Authorization").substring(7);
        }catch(Exception e){
            throw new BadRequestException("Token is null");
        }
        DecodedJWT decodedToken = JWTUtils.decodeToken(token);
        if(!decodedToken.getClaim("userType").asString().equals("trainee")){
            throw new UnauthorizedException("User is not a trainee");
        }

        //check the validity of the token
        JWTUtils.validateToken(decodedToken);

        //check if the userid in the token is the same as the userid in the path
        String id= UrlIdExtractor.extract(request.getRequestURI(), "/trainee/");
        if(!decodedToken.getClaim("userId").asString().equals(id)){
            throw new UnauthorizedException("User id in token is not the same as the user id in the path");
        }

        return true;
    }
}

package com.lukefitness.lukegymbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.UrlIdExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SendVerifyEmailInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        //
        String token;
        try{
            token=request.getHeader("Authorization").substring(7);
        }catch(Exception e){
            throw new UnauthorizedException("Token is null");
        }
        String url=request.getRequestURI();
        DecodedJWT decodedToken = JWTUtils.decodeToken(token);
        String prefix;

        //check the user type
        if(url.contains("trainee")){
            prefix="/trainee/";
            if(!decodedToken.getClaim("userType").asString().equals("trainee")){
                throw new UnauthorizedException("User is not a trainee");
            }
        }else if(url.contains("trainer")){
            prefix="/trainer/";
            if(!decodedToken.getClaim("userType").asString().equals("trainer")
                    || !decodedToken.getClaim("userType").asString().equals("admin")
            ){
                throw new UnauthorizedException("User is not a trainer/admin");
            }
        }else {
            throw new UnauthorizedException("User is not a trainee or trainer");
        }

        //check the validity of the token
        JWTUtils.validateTokenForVerifyEmail(decodedToken);

        //check if the userid in the token is the same as the userid in the path
        String id= UrlIdExtractor.extract(url, prefix);
        if(!decodedToken.getClaim("userId").asString().equals(id)){
            throw new UnauthorizedException("User id in token is not the same as the user id in the path");
        }

        return true;
    }

}

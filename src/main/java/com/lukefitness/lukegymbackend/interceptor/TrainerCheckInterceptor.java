package com.lukefitness.lukegymbackend.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TrainerCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //check the token first
        String token=request.getHeader("Authorization").substring(7);

        DecodedJWT decodedToken = JWTUtils.decodeToken(token);

        //check the validity of the token
        JWTUtils.validateToken(decodedToken);

        //check the role
        if(decodedToken.getClaim("userType").asString().equals("trainer")){
            //check if the userid in the token is the same as the userid in the path
            String id=trainerIdExtractor(request.getRequestURI());
            if(!decodedToken.getClaim("userId").asString().equals(id)){
                throw new UnauthorizedException("User id in token is not the same as the user id in the path");
            }
        }else if(decodedToken.getClaim("userType").asString().equals("admin")){
            //do nothing
        }else {
            throw new UnauthorizedException("User is not authorized to access this resource");
        }

        return true;
    }

    private String trainerIdExtractor(String pathInfo){
        String prefix="/trainer/";
        int index=pathInfo.indexOf(prefix);
        String restStr=pathInfo.substring(index+prefix.length());
        int end=restStr.indexOf("/");
        return restStr.substring(0, end);
    }
}

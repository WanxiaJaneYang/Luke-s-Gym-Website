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
public class TraineeCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //check the token first
        String token=request.getHeader("Authorization").substring(7);
        if (token==null){
            throw new BadRequestException("Token is null");
        }

        DecodedJWT decodedToken = JWTUtils.decodeToken(token);
        if(decodedToken==null){
            throw new UnauthorizedException("Token is invalid");
        }else if(!decodedToken.getClaim("userType").asString().equals("trainee")){
            throw new UnauthorizedException("User is not a trainee");
        }

        //check the validity of the token
        JWTUtils.validateToken(decodedToken);

        //check if the userid in the token is the same as the userid in the path
        String id=traineeIdExtractor(request.getRequestURI());
        if(!decodedToken.getClaim("userId").asString().equals(id)){
            throw new UnauthorizedException("User id in token is not the same as the user id in the path");
        }

        return true;
    }

    private String traineeIdExtractor(String pathInfo){
        String prefix="/trainee/";
        int index=pathInfo.indexOf(prefix);
        String restStr=pathInfo.substring(index+prefix.length());
        int end=restStr.indexOf("/");
        String idStr=restStr.substring(0, end);
        return idStr;
    }
}

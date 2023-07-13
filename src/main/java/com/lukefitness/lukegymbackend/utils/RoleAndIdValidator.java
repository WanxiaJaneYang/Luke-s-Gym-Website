package com.lukefitness.lukegymbackend.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lukefitness.lukegymbackend.exception.UnauthorizedException;
import jakarta.validation.constraints.NotNull;

public class RoleAndIdValidator {
    public static void validate(@NotNull String url, @NotNull DecodedJWT decodedToken){
        String requiredUserType;
        String requiredUserId;

        //get the required user type
        if(url.contains("/trainee/")){
            requiredUserType="trainee";
        }else if(url.contains("/trainer/")){
            requiredUserType="trainer";
        }else if(url.contains("/admin/")){
            requiredUserType="admin";
        }else {
            throw new UnauthorizedException("User is not a trainee or trainer or admin");
        }

        //get the required user id
        requiredUserId= UrlIdExtractor.extract(url, "/"+requiredUserType+"/");

        //compare the required user type and userType with the token
        if(!decodedToken.getClaim("userType").asString().equals(requiredUserType)){
            throw new UnauthorizedException("User is not a "+requiredUserType);
        }

        //compare the required user id and userId with the token
        if(!requiredUserType.equals("admin")){
            if(!decodedToken.getClaim("userId").asString().equals(requiredUserId)){
                throw new UnauthorizedException("User id in token is not the same as the user id in the path");
            }
        }
    }
}

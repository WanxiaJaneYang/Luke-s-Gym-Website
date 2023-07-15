package com.lukefitness.lukegymbackend.aspect;

import com.lukefitness.lukegymbackend.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class ControllerAspect {

    @Around("(@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)) && " +
            "!within(com.lukefitness.lukegymbackend.controller.login..*) && " +
            "!within(com.lukefitness.lukegymbackend.controller.send_rest_pw_email..*) && " +
            "!within(com.lukefitness.lukegymbackend.controller.verify..*)")
    public Object addTokenToResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestPath = request.getRequestURI();
        if (requestPath.startsWith("/swagger") || requestPath.startsWith("/v3/api-docs")) {
            return proceedingJoinPoint.proceed();
        }

        String oldToken = request.getHeader("Authorization");
        if (oldToken != null && oldToken.startsWith("Bearer ")) {
            oldToken = oldToken.substring(7);
        }

        Object returnedObject = proceedingJoinPoint.proceed();

        if (returnedObject instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) returnedObject;
            if(!responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity;
            }

            String newToken = JWTUtils.getNewToken(oldToken);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + newToken);
            return ResponseEntity.status(responseEntity.getStatusCode()).headers(headers).body(responseEntity.getBody());
        }

        return returnedObject;
    }
}

package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.models.User;
import com.lukefitness.lukegymbackend.service.UserService;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        System.out.println("get register request, user name: "+user.getName());
        User userTemp = userService.registerService(user);
        if(userTemp!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userTemp);
        }
        else{
            Map<String, String > errorMsg=Collections.singletonMap("error",MessageEnum.USER_ALREADY_EXISTS.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        User userTemp = userService.loginService(user.getName(),user.getPassword());
        if(userTemp!=null){
            String token= JWTUtils.getToken(String.valueOf(userTemp.getId()),userTemp.getName(),userTemp.getUser_type());
            Map<String, Object> userMap=new HashMap<>();
            userMap.put("id",userTemp.getId());
            userMap.put("name",userTemp.getName());
            userMap.put("token",token);
            return ResponseEntity.status(HttpStatus.OK).body(userMap);
        }
        else{
            Map<String, String > errorMsg=Collections.singletonMap("error",MessageEnum.LOGIN_FAIL.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMsg);
        }
    }
}

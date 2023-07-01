package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.models.User;
import com.lukefitness.lukegymbackend.service.UserService;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.Result;
import com.lukefitness.lukegymbackend.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired(required = false)
    UserService userService;

    @RequestMapping("/register")
    public Result<User> register(@RequestBody User user){
        User userTemp = userService.registerService(user);
        if(userTemp!=null){
            return Result.success(userTemp);
        }
        else{
            return Result.error(ResultCodeEnum.USER_ALREADY_EXISTS);
        }
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        User userTemp = userService.loginService(user.getName(),user.getPassword());
        if(userTemp!=null){
            String token= JWTUtils.getToken(String.valueOf(userTemp.getId()),userTemp.getName(),userTemp.getUser_type());
            Map<String, Object> userMap=new HashMap<>();
            userMap.put("id",userTemp.getId());
            userMap.put("name",userTemp.getName());
            userMap.put("token",token);
            return Result.success(userMap);
        }
        else{
            return Result.error(ResultCodeEnum.UNAUTHORIZED, "Username or password is wrong");
        }
    }
}

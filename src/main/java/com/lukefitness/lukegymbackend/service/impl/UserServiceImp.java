package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.UserDao;
import com.lukefitness.lukegymbackend.models.User;
import com.lukefitness.lukegymbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired(required = false)
    UserDao userDao;

    @Override
    public User registerService(User user) {
        User userTemp = userDao.getUserByName(user.getName());
        if(userTemp!=null){
            return null;
        }
        else{
            userDao.registerUser(user);
            return user;
        }
    }

    @Override
    public User loginService(String name, String password) {
        return userDao.login(name,password);
    }
}

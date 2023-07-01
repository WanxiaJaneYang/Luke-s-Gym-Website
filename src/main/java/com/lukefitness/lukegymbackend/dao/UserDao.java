package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int registerUser(User user);
    User getUserByName(String name);
    User login(String name, String password);

}

package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerService(User user);
    User loginService(String name, String password);
}

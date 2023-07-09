package com.lukefitness.lukegymbackend.models.request.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {
    String username;
    String password;
    String email;
}

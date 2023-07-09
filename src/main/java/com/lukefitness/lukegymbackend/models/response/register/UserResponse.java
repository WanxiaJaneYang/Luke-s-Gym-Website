package com.lukefitness.lukegymbackend.models.response.register;

import com.lukefitness.lukegymbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    int id;
    String username;
    String email;
    boolean email_verified;

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.email_verified = user.isEmail_verified();
    }

}

package com.lukefitness.lukegymbackend.models;

import lombok.Data;

@Data
public class User {
    /*with lombok, we don't need to write getters and setters for the fields
    * we just need to write the fields and lombok will generate the getters and setters for us
     */
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String user_type;
}

package com.lukefitness.lukegymbackend.models.response;

public class UserResponse {
    private int id;
    private String username;
    private String email;
    private boolean email_verified;

    public UserResponse() {
    }

    public UserResponse(int id, String username, String email, boolean email_verified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.email_verified = email_verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }
}

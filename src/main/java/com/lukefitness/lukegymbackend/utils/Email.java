package com.lukefitness.lukegymbackend.utils;

import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;

public class Email {
    private String to;
    private String subject;
    private String text;

    private static final String resetPasswordLink = "http://localhost:3000/reset-password";
    private static final String verifyEmailLink = "http://localhost:3000/verify-email";

    public Email() {
    }

    public Email(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Email getResetPasswordEmail(String to, String username,int tokenId, String token) {
        String subject = "Password Reset Request";
        String resetPasswordLinkWithToken = resetPasswordLink+"?id="+tokenId+"&token="+token;
        String text="" +
                "<h1>Hi "+username+"</h1>" +
                "<p>We received a request to reset your password for your account associated with this e-mail address.</p>" +
                "<p>If you made this request, please click on the link below or paste this into your browser to complete the process:</p>" +
                "<p><a href=\""+resetPasswordLinkWithToken+"\">"+resetPasswordLinkWithToken+"</a></p>" +
                "<p>This link will expire in 15 minutes.</p>" +
                "<p>If you did not request a password reset, please ignore this email</p>" +
                "<p>Thanks,</p>" +
                "<p>Luke Fitness</p>";
        Email email = new Email(to, subject, text);
        return email;
    }

    public static Email getVerifyEmailAddressEmail(String to, String username,int tokenId, String token){
        String subject = "Please Verify Your Email Address";
        String verifyEmailLinkWithToken = verifyEmailLink+"?id="+tokenId+"&token="+token;
        String text="" +
                "<h1>Hi "+username+"</h1>" +
                "<p>Thanks for signing up for Luke Fitness! We're excited to have you as an early user.</p>" +
                "<p>Before you can start using your account, you need to verify your email address by clicking the link below:</p>" +
                "<p><a href=\""+verifyEmailLinkWithToken+"\">"+verifyEmailLinkWithToken+"</a></p>" +
                "<p>This link will expire in 15 minutes.</p>" +
                "<p>If you did not sign up for Luke Fitness, please ignore this email</p>" +
                "<p>Thanks,</p>" +
                "<p>Luke Fitness</p>";
        return new Email(to, subject, text);
    }

    public static Email getVerifyEmailByTrainer(Trainer trainer, EmailToken tokenRecord){
        String to = trainer.getEmail();
        String username = trainer.getUsername();
        String token= tokenRecord.getToken();
        return getVerifyEmailAddressEmail(to, username,tokenRecord.getId(), token);
    }

    public static Email getResetPwEmailByTrainer(Trainer trainer, EmailToken tokenRecord){
        String to = trainer.getEmail();
        String username = trainer.getUsername();
        String token= tokenRecord.getToken();
        return getResetPasswordEmail(to, username, tokenRecord.getId(), token);
    }

    public static Email getVerifyEmailByTrainee(Trainee trainee, EmailToken tokenRecord){
        String to = trainee.getEmail();
        String username = trainee.getUsername();
        String token= tokenRecord.getToken();
        return getVerifyEmailAddressEmail(to, username, tokenRecord.getId(),token);
    }

    public static Email getResetPwEmailByTrainee(Trainee trainee, EmailToken tokenRecord){
        String to = trainee.getEmail();
        String username = trainee.getUsername();
        String token= tokenRecord.getToken();
        return getResetPasswordEmail(to, username, tokenRecord.getId(), token);
    }
}

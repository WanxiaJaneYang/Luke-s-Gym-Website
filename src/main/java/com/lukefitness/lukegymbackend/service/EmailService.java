package com.lukefitness.lukegymbackend.service;

import com.lukefitness.lukegymbackend.utils.Email;

public interface EmailService {

    public void sendVerifyEmailToTrainee(int traineeId) throws Exception;
    public void sendResetPwEmailToTraineeByUsername(String username) throws Exception;
    public void sendResetPwEmailToTraineeByEmail(String email) throws Exception;
    public void sendVerifyEmailToTrainer(int trainerId);
    public void sendResetPwEmailToTrainerByUsername(String username);
    public void sendResetPwEmailToTrainerByEmail(String email);

}

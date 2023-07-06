package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailTokenDao emailTokenDao;

    @Autowired
    TraineeService traineeService;

    @Autowired
    TrainerService trainerService;

    @Value("${spring.mail.username}")
    private String from;

    private void sendEmail(Email email) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(),true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Transactional
    private void sendEmailToTrainee(Map<String,String> map) throws Exception {
        Trainee trainee;
        Email email;

        // get trainee by username or email address or traineeId
        if (map.containsKey("username")) {
            trainee = traineeService.getTraineeByUsername(map.get("username"));
        }else if (map.containsKey("emailAddress")) {
            trainee = traineeService.getTraineeByEmail(map.get("emailAddress"));
        }else{
            trainee=traineeService.getTraineeById(Integer.parseInt(map.get("traineeId")));
        }
        // get email by emailType
        if (map.get("emailType").equals("resetPassword")){
            email=Email.getResetPwEmailByTrainee(trainee);
        }else{
            email=Email.getVerifyEmailByTrainee(trainee);
        }

        EmailToken emailToken=new EmailToken(trainee);
        emailTokenDao.insertEmailToken(emailToken);
        sendEmail(email);
    }

    @Override
    public void sendVerifyEmailToTrainee(int traineeId) throws Exception {
        Map<String,String> map=Map.of("traineeId",String.valueOf(traineeId),"emailType","verifyEmail");
        sendEmailToTrainee(map);
    }

    @Override
    public void sendResetPwEmailToTraineeByUsername(String username) throws Exception {
        Map<String,String> map=Map.of("username",username,"emailType","resetPassword");
        sendEmailToTrainee(map);
    }

    @Override
    public void sendResetPwEmailToTraineeByEmail(String email) throws Exception {
        Map<String,String> map=Map.of("emailAddress",email,"emailType","resetPassword");
        sendEmailToTrainee(map);
    }

    @Transactional
    private void sendEmailToTrainer(Map<String,String> map) {
        Trainer trainer;
        Email email;

        //get the trainer by username, email or trainerId
        if(map.containsKey("username")){
            trainer = trainerService.getTrainerByUsername(map.get("username"));
        }else if (map.containsKey("emailAddress")){
            trainer = trainerService.getTrainerByEmail(map.get("emailAddress"));
        }else{
            trainer = trainerService.getTrainerById(Integer.parseInt(map.get("trainerId")));
        }

        //get the email by emailType
        if (map.get("emailType").equals("resetPassword")){
            email=Email.getResetPwEmailByTrainer(trainer);
        }else{
            email=Email.getVerifyEmailByTrainer(trainer);
        }
        EmailToken emailToken=new EmailToken(trainer);
        emailTokenDao.insertEmailToken(emailToken);
        sendEmail(email);
    }
    @Override
    public void sendVerifyEmailToTrainer(int trainerId) {
        Map<String,String> map=Map.of("trainerId",String.valueOf(trainerId),"emailType","verifyEmail");
        sendEmailToTrainer(map);
    }
    @Override
    public void sendResetPwEmailToTrainerByUsername(String username) {
        Map<String,String> map=Map.of("username",username,"emailType","resetPassword");
        sendEmailToTrainer(map);
    }

    @Override
    public void sendResetPwEmailToTrainerByEmail(String email) {
        Map<String,String> map=Map.of("emailAddress",email,"emailType","resetPassword");
        sendEmailToTrainer(map);
    }

}

package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.models.EmailToken;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.models.User;
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
    TraineeDao traineeDao;

    @Autowired
    TrainerDao trainerDao;

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
            trainee = traineeDao.getTraineeByUsername(map.get("username"));
        }else if (map.containsKey("emailAddress")) {
            trainee = traineeDao.getTraineeByEmail(map.get("emailAddress"));
        }else{
            trainee=traineeDao.getTraineeById(Integer.parseInt(map.get("traineeId")));
        }
        if (trainee==null){
            throw new Exception("Trainee not exist");
        }
        EmailToken emailToken=new EmailToken(trainee);
        emailTokenDao.insertEmailToken(emailToken);
        // get email by emailType
        if (map.get("emailType").equals("resetPassword")){
            email=Email.getResetPwEmailByTrainee(trainee,emailToken);
        }else{
            email=Email.getVerifyEmailByTrainee(trainee,emailToken);
        }
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
            trainer = trainerDao.getTrainerByName(map.get("username"));
        }else if (map.containsKey("emailAddress")){
            trainer = trainerDao.getTrainerByEmail(map.get("emailAddress"));
        }else{
            trainer = trainerDao.getTrainerById(Integer.parseInt(map.get("trainerId")));
        }
        EmailToken emailToken=new EmailToken(trainer);
        emailTokenDao.insertEmailToken(emailToken);
        //get the email by emailType
        if (map.get("emailType").equals("resetPassword")){
            email=Email.getResetPwEmailByTrainer(trainer, emailToken);
        }else{
            email=Email.getVerifyEmailByTrainer(trainer, emailToken);
        }
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

    @Override
    public void sendResetSuccessEmail(User user) {
        Email email=Email.getResetPwSuccessEmail(user.getEmail(),user.getUsername());
        sendEmail(email);
    }

    @Override
    public void sendTrainerDeletionEmail(Trainer trainer) {
        Email email=Email.getTrainerDeleteEmail(trainer.getEmail(),trainer.getUsername());
        sendEmail(email);
    }

    @Override
    public void sendTraineeDeletionEmail(Trainee trainee) {
        Email email=Email.getTraineeDeleteEmail(trainee.getEmail(),trainee.getUsername());
        sendEmail(email);
    }

}

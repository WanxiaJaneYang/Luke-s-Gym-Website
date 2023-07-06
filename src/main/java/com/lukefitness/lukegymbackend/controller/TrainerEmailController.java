package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.service.EmailService;
import com.lukefitness.lukegymbackend.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/trainer")
public class TrainerEmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/{trainerId}/verify")
    public ResponseEntity<?> sendVerifyEmail(@PathVariable int trainerId) {
        try {
            emailService.sendVerifyEmailToTrainer(trainerId);
            return Response.success("Email sent successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch(NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> sendResetEmail(@RequestParam String username, @RequestParam String email) {
        try {
            if (username != null && !username.isEmpty()) {
                emailService.sendResetPwEmailToTrainerByUsername(username);
            } else if (email != null && !email.isEmpty()) {
                emailService.sendResetPwEmailToTrainerByEmail(email);
            } else {
                throw new BadRequestException("Username or email is required");
            }
            return Response.success("Email sent successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch(NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage());
        }
    }
}

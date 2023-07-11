package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.dao.TraineeDao;
import com.lukefitness.lukegymbackend.dao.TrainerDao;
import com.lukefitness.lukegymbackend.service.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasksImp implements ScheduledTasks {
    @Autowired
    EmailTokenDao emailTokenDao;

    @Autowired
    TrainerDao trainerDao;

    @Autowired
    TraineeDao traineeDao;

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteExpiredEmailTokens() {
        emailTokenDao.deleteEmailToken();
        System.out.println("delete expired email tokens");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deactivateTrainees() {
        traineeDao.deactivateTrainee();
        System.out.println("deactivate trainees");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deactivateTrainers() {
        trainerDao.deactivateTrainer();
        System.out.println("deactivate trainers");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteNotActiveTrainees() {
        traineeDao.deleteNotActiveTrainees();
        System.out.println("delete not active trainees");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteNotActiveTrainers() {
        trainerDao.deleteNotActiveTrainers();
        System.out.println("delete not active trainers");
    }
}

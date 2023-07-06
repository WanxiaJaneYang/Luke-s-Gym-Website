package com.lukefitness.lukegymbackend.service.impl;

import com.lukefitness.lukegymbackend.dao.EmailTokenDao;
import com.lukefitness.lukegymbackend.service.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasksImp implements ScheduledTasks {
    @Autowired
    EmailTokenDao emailTokenDao;

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteExpiredEmailTokens() {
        try{
            emailTokenDao.deleteEmailToken();
            System.out.println("delete expired email tokens");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

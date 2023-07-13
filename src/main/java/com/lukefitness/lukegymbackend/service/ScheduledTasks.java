package com.lukefitness.lukegymbackend.service;

public interface ScheduledTasks {
    void deleteExpiredEmailTokens();

    void deactivateTrainers();
    void deleteNotActiveTrainees();
    void deleteNotActiveTrainers();
}

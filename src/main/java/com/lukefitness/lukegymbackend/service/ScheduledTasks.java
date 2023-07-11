package com.lukefitness.lukegymbackend.service;

public interface ScheduledTasks {
    void deleteExpiredEmailTokens();

    void deactivateTrainees();
    void deactivateTrainers();
    void deleteNotActiveTrainees();
    void deleteNotActiveTrainers();
}

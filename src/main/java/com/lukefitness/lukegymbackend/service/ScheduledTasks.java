package com.lukefitness.lukegymbackend.service;

public interface ScheduledTasks {
    public void deleteExpiredEmailTokens();

    public void deactivateTrainees();
    public void deactivateTrainers();
    public void deleteNotActiveTrainees();
    public void deleteNotActiveTrainers();
}

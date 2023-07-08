package com.lukefitness.lukegymbackend.models.response;

import com.lukefitness.lukegymbackend.models.Trainee;
public class TraineeResponse extends UserResponse{
    private int trainer_id;

    public TraineeResponse() {
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public TraineeResponse(int id, String username, String email, boolean email_verified) {
        super(id, username, email, email_verified);
    }

    public TraineeResponse(int id, String username, String email, boolean email_verified, int trainer_id) {
        super(id, username, email, email_verified);
        this.trainer_id = trainer_id;
    }
    public TraineeResponse(Trainee trainee){
        super(trainee.getId(), trainee.getUsername(), trainee.getEmail(), trainee.isEmail_verified());
        this.trainer_id = trainee.getTrainer_id();
    }
}

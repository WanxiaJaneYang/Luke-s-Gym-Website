package com.lukefitness.lukegymbackend.dto.response.register;

import com.lukefitness.lukegymbackend.models.Trainee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TraineeResponse extends UserResponse {
    int trainer_id;

    public TraineeResponse(Trainee trainee){
        super(trainee);
        this.trainer_id = trainee.getTrainer_id();
    }
}

package com.lukefitness.lukegymbackend.models.response.register;

import com.lukefitness.lukegymbackend.models.Trainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerResponse extends UserResponse {
    private boolean is_admin;

    public TrainerResponse(Trainer trainer){
        super(trainer);
        this.is_admin = trainer.is_admin();
    }
}

package com.lukefitness.lukegymbackend.dto.response;

import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendProgramCardRes {
    ProgramCard programCard;
    Program program;
}

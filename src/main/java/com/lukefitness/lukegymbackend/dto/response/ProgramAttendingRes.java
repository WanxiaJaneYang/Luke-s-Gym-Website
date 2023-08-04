package com.lukefitness.lukegymbackend.dto.response;

import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramFeedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProgramAttendingRes {
    ProgramFeedback programFeedback;
    Program program;

}

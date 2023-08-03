package com.lukefitness.lukegymbackend.service.program_feedback;

import com.lukefitness.lukegymbackend.models.ProgramFeedback;

public interface ProgramFeedbackService {
    ProgramFeedback getProgramFeedbackByProgram(Integer programId);
    ProgramFeedback getProgramFeedbackByFeedbackId(Integer feedbackId);

    ProgramFeedback updateTrainerFeedback(Integer feedbackId, String trainerFeedback);
    ProgramFeedback updateTraineeFeedback(Integer feedbackId, float score, String traineeFeedback);
}

package com.lukefitness.lukegymbackend.service.impl.program_feedback;

import com.lukefitness.lukegymbackend.dao.ProgramDao;
import com.lukefitness.lukegymbackend.dao.ProgramFeedbackDao;
import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramFeedback;
import com.lukefitness.lukegymbackend.models.ProgramFeedbackExample;
import com.lukefitness.lukegymbackend.service.program_feedback.ProgramFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgramFeedbackServiceImp implements ProgramFeedbackService {
    @Autowired
    ProgramFeedbackDao programFeedbackDao;

    @Autowired
    ProgramDao programDao;
    @Override
    public ProgramFeedback getProgramFeedbackByProgram(Integer programId) {
        ProgramFeedbackExample programFeedbackExample = new ProgramFeedbackExample();
        programFeedbackExample.createCriteria().andProgramIdEqualTo(programId);
        if (programFeedbackDao.selectByExample(programFeedbackExample).isEmpty())
            throw new NotFoundException("Program feedback not found for program with id: " + programId.toString());
        return programFeedbackDao.selectByExample(programFeedbackExample).get(0);
    }

    @Override
    public ProgramFeedback getProgramFeedbackByFeedbackId(Integer feedbackId) {
        ProgramFeedback programFeedback = programFeedbackDao.selectByPrimaryKey(feedbackId);
        if (programFeedback == null)
            throw new NotFoundException("Program feedback not found with id: " + feedbackId.toString());
        return programFeedback;
    }

    @Override
    public ProgramFeedback updateTrainerFeedback(Integer feedbackId, String trainerFeedback) {
        ProgramFeedback programFeedback = new ProgramFeedback();
        programFeedback.setFeedbackId(feedbackId);
        programFeedback.setTrainerFeedback(trainerFeedback);
        if (programFeedbackDao.updateByPrimaryKeySelective(programFeedback) == 0)
            throw new NotFoundException("Program feedback not found with id: " + feedbackId.toString());
        return programFeedbackDao.selectByPrimaryKey(feedbackId);
    }

    @Override
    @Transactional
    public ProgramFeedback updateTraineeFeedback(Integer feedbackId, float score, String traineeFeedback) {
        ProgramFeedback programFeedback = new ProgramFeedback();
        programFeedback.setFeedbackId(feedbackId);
        programFeedback.setScore(score);
        programFeedback.setTraineeFeedback(traineeFeedback);
        if (programFeedbackDao.updateByPrimaryKeySelective(programFeedback) == 0)
            throw new NotFoundException("Program feedback not found with id: " + feedbackId.toString());

        // update related program
        Program program=new Program();
        program.setProgramId(programFeedbackDao.selectByPrimaryKey(feedbackId).getProgramId());
        program.setFeedbackGiven(true);
        if (programDao.updateByPrimaryKeySelective(program) == 0)
            throw new BadRequestException("Program feedback not updated with id: " + feedbackId.toString());
        return programFeedbackDao.selectByPrimaryKey(feedbackId);
    }
}

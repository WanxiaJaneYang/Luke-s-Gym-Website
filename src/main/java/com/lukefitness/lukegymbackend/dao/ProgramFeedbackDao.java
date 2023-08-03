package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.ProgramFeedback;
import com.lukefitness.lukegymbackend.models.ProgramFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramFeedbackDao {
    long countByExample(ProgramFeedbackExample example);

    int deleteByExample(ProgramFeedbackExample example);

    int deleteByPrimaryKey(Integer feedbackId);

    int insert(ProgramFeedback row);

    int insertSelective(ProgramFeedback row);

    List<ProgramFeedback> selectByExampleWithBLOBs(ProgramFeedbackExample example);

    List<ProgramFeedback> selectByExample(ProgramFeedbackExample example);

    ProgramFeedback selectByPrimaryKey(Integer feedbackId);

    int updateByExampleSelective(@Param("row") ProgramFeedback row, @Param("example") ProgramFeedbackExample example);

    int updateByExampleWithBLOBs(@Param("row") ProgramFeedback row, @Param("example") ProgramFeedbackExample example);

    int updateByExample(@Param("row") ProgramFeedback row, @Param("example") ProgramFeedbackExample example);

    int updateByPrimaryKeySelective(ProgramFeedback row);

    int updateByPrimaryKeyWithBLOBs(ProgramFeedback row);

    int updateByPrimaryKey(ProgramFeedback row);
}
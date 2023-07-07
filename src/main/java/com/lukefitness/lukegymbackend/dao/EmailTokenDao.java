package com.lukefitness.lukegymbackend.dao;

import com.lukefitness.lukegymbackend.models.EmailToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface EmailTokenDao {
    public void insertEmailToken(EmailToken emailToken);
    public EmailToken getEmailToken(EmailToken emailToken);
    public void deleteEmailToken();
    public void deleteEmailTokenById(int id);
}

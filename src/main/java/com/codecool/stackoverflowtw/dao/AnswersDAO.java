package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswersDAO {
    public List<Answer> getAllAnswersByQuestionId(int searchedQuestionId);

    public Answer getAnswerById(int id);


    public boolean deleteAnswerById(int id);

    public int addNewAnswer(Answer newQuestion);
}

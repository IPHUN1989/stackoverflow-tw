package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    public List<Question> getAllQuestions();

    public Question getQuestionById(int id);


    public boolean deleteQuestionById(int id);

    public int addNewQuestion(Question newQuestion);
}

package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;
    private final AnswersDAO answersDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO, AnswersDAO answersDAO) {
        this.questionsDAO = questionsDAO;
        this.answersDAO = answersDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionsDAO.getAllQuestions().stream().map(this::transformDAOToDTO).toList();
    }

    public QuestionDTO getQuestionById(int id) {
       return transformDAOToDTO(questionsDAO.getQuestionById(id));
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionById(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(new Question(-1, question.userId(),
                question.title(), question.description(), LocalDateTime.now()));
    }

    private QuestionDTO transformDAOToDTO(Question question) {
        return new QuestionDTO(question.id(), question.title(), question.description(),
                answersDAO.getAllAnswersByQuestionId(question.id()), question.created());
    }
}

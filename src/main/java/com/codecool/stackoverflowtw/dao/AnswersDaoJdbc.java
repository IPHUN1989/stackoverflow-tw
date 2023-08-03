package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.conneciton.DatabaseConnectionProvider;
import com.codecool.stackoverflowtw.dao.model.Answer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AnswersDaoJdbc implements AnswersDAO{

    private final DatabaseConnectionProvider databaseConnectionProvider;

    public AnswersDaoJdbc(DatabaseConnectionProvider databaseConnectionProvider) {
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    @Override
    public List<Answer> getAllAnswersByQuestionId(int searchedQuestionId) {
        String sql = "SELECT * FROM answers WHERE question_id = ?";
        List<Answer> answers = new ArrayList<>();

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                 pstmt.setInt(1, searchedQuestionId);
                ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int question_id = rs.getInt("question_id");
                int user_id = rs.getInt("user_id");
                String description = rs.getString("description");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                boolean accepted = rs.getBoolean("accepted");
                answers.add(new Answer(id, question_id, user_id, description, created, accepted));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return answers;
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT * FROM answers WHERE id = ?";


        Answer foundAnswer = null;

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int answer_id = rs.getInt("id");
            int questionId = rs.getInt("question_id");
            int user_id = rs.getInt("user_id");
            String description = rs.getString("description");
            LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
            boolean accepted = rs.getBoolean("accepted");
            foundAnswer = new Answer(answer_id, questionId, user_id, description, created, accepted);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return foundAnswer;
    }

    @Override
    public boolean deleteAnswerById(int id) {

        String sql = "DELETE FROM answers WHERE id = ?";

        boolean successfull = false;

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                successfull = true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return successfull;
    }

    @Override
    public int addNewAnswer(Answer newAnswer) {

        int question_id = newAnswer.questionId();
        int user_id = newAnswer.userId();
        String description = newAnswer.description();
        Timestamp timestamp = Timestamp.valueOf(newAnswer.created());
        boolean accepted = newAnswer.accepted();

        String sql = "INSERT INTO answers (question_id, user_id, description, created, accepted)  VALUES(?, ?, ?, ?, ?)";
        int createdId = 0;


        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, question_id);
            pstmt.setInt(2, user_id);
            pstmt.setString(3, description);
            pstmt.setTimestamp(4, timestamp);
            pstmt.setBoolean(5, accepted);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                createdId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return createdId;
    }
}

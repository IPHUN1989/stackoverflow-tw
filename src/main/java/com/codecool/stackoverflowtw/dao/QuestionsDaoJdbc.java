package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.conneciton.DatabaseConnectionProvider;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {

    public QuestionsDaoJdbc(DatabaseConnectionProvider databaseConnectionProvider) {
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    private final DatabaseConnectionProvider databaseConnectionProvider;

    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM question";
        List<Question> questionsList = new ArrayList<>();

        try (Connection conn = databaseConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                questionsList.add(new Question(id, user_id, title, description, created));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return questionsList;
    }

    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM question WHERE id = ?";
        
        Question foundQuestion = null;

        try (Connection conn = databaseConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int questionId = rs.getInt("id");
            int user_id = rs.getInt("user_id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
            foundQuestion = new Question(questionId, user_id, title, description, created);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return foundQuestion;
    }

    public boolean deleteQuestionById(int id) {
        String sql = "DELETE * FROM question WHERE id = ?";

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int addNewQuestion(Question newQuestion) {
        int user_id = newQuestion.userId();
        String title = newQuestion.title();
        String description = newQuestion.description();

        String sql = "INSERT INTO (user_id, title, description) question  VALUES(?, ?, ?)";

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user_id);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        int createdId = 0;
        return createdId;
    }
}

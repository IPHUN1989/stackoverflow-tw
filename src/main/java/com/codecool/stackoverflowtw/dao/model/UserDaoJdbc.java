package com.codecool.stackoverflowtw.dao.model;

import com.codecool.stackoverflowtw.dao.UserDAO;
import com.codecool.stackoverflowtw.dao.conneciton.DatabaseConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDAO {
    private final DatabaseConnectionProvider databaseConnectionProvider;

    public UserDaoJdbc(DatabaseConnectionProvider databaseConnectionProvider) {
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM sof_users";
        List<User> users = new ArrayList<>();

        try (Connection conn = databaseConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");

                users.add(new User(id, name, password));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM sof_users WHERE id = ?";

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            rs.next();

            int userId = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");

            return new User(userId, name, password);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM sof_users WHERE id = ?";

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public int addNewUser(User user) {
        int createdId = 0;

        String name = user.userName();
        String password = user.password();

        String sql = "INSERT INTO sof_users (name, password)  VALUES(?, ?)";

        try (Connection conn = databaseConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();

            createdId = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return createdId;
    }
}

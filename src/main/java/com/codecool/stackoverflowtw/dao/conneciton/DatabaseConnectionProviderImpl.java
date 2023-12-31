package com.codecool.stackoverflowtw.dao.conneciton;

import java.sql.Connection;
import java.sql.*;

public class DatabaseConnectionProviderImpl implements DatabaseConnectionProvider {

    public static final String APP_DB_HOST = System.getenv("APP_DB_HOST");
    public static final String APP_DB_PORT = System.getenv("APP_DB_PORT");
    public static final String APP_DB_NAME = System.getenv("APP_DB_NAME");
    public static final String URL = String.format("jdbc:postgresql://%s:%s/%s", APP_DB_HOST, APP_DB_PORT, APP_DB_NAME);
    public static final String USER_NAME = System.getenv("APP_DB_USER");
    public static final String PASSWORD = System.getenv("APP_DB_PASSWORD");

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}

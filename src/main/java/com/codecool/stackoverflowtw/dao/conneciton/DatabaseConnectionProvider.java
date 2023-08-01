package com.codecool.stackoverflowtw.dao.conneciton;

import java.sql.Connection;

public interface DatabaseConnectionProvider {
    Connection getConnection(String url, String user, String password);
}

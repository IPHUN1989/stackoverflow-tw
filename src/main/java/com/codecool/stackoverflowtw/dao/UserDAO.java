package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.dao.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public User getUserById(int id);


    public boolean deleteUserById(int id);

    public int addNewUser(User user);
}

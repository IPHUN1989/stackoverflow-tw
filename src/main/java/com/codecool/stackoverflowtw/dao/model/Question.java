package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public record Question(int id, int userId, String title, String description, LocalDateTime created) {
}

package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public record Answer(int id, int questionId, int userId, String description, LocalDateTime created, boolean accepted) {
}

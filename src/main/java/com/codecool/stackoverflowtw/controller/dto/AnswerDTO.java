package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int question_id, int user_id, String description, LocalDateTime created, boolean accepted) {}

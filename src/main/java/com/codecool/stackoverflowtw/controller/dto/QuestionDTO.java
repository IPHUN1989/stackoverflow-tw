package com.codecool.stackoverflowtw.controller.dto;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionDTO(int id, String title, String description, List<Answer> answers, LocalDateTime created) {}

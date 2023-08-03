package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record NewAnswerDTO(int question_id, int user_id, String description, String created, boolean accepted){}

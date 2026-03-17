package com.youwillwin.dto;

import com.youwillwin.model.Difficulty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record QuestionRequest(
    @NotBlank String title,
    @NotBlank String description,
    @NotNull Difficulty difficulty,
    String starterCode,
    Set<String> tags,
    @Valid List<TestCaseRequest> testCases
) {}

package com.youwillwin.dto;

import jakarta.validation.constraints.NotBlank;

public record TestCaseRequest(
    @NotBlank String input,
    @NotBlank String expectedOutput,
    boolean sample,
    int orderIndex,
    String displayInput,
    String displayOutput
) {}

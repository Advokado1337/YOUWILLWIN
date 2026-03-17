package com.youwillwin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExecutionRequest(
    @NotNull Long questionId,
    @NotBlank String code,
    Long timeSpentMs
) {}

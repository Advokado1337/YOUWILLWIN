package com.youwillwin.dto;

public record SubmitResponse(
    ExecutionResult execution,
    AttemptResponse attempt
) {}

package com.youwillwin.dto;

import com.youwillwin.model.Attempt;

import java.time.LocalDateTime;

public record AttemptResponse(
    Long id,
    Long questionId,
    String questionTitle,
    String status,
    int passedCount,
    int totalCount,
    Long timeSpentMs,
    LocalDateTime executedAt
) {
    public static AttemptResponse from(Attempt a) {
        return new AttemptResponse(
            a.getId(),
            a.getQuestion().getId(),
            a.getQuestion().getTitle(),
            a.getStatus(),
            a.getPassedCount(),
            a.getTotalCount(),
            a.getTimeSpentMs(),
            a.getExecutedAt()
        );
    }
}

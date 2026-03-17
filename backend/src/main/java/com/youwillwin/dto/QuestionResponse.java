package com.youwillwin.dto;

import com.youwillwin.model.Difficulty;
import com.youwillwin.model.Question;
import com.youwillwin.model.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record QuestionResponse(
    Long id,
    String title,
    String description,
    Difficulty difficulty,
    String methodSignature,
    Set<String> tags,
    List<TestCaseResponse> testCases,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static QuestionResponse from(Question q) {
        return new QuestionResponse(
            q.getId(),
            q.getTitle(),
            q.getDescription(),
            q.getDifficulty(),
            q.getMethodSignature(),
            q.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
            q.getTestCases().stream().map(TestCaseResponse::from).toList(),
            q.getCreatedAt(),
            q.getUpdatedAt()
        );
    }
}

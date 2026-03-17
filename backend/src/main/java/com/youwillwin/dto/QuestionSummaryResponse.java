package com.youwillwin.dto;

import com.youwillwin.model.Difficulty;
import com.youwillwin.model.Question;
import com.youwillwin.model.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public record QuestionSummaryResponse(
    Long id,
    String title,
    Difficulty difficulty,
    Set<String> tags
) {
    public static QuestionSummaryResponse from(Question q) {
        return new QuestionSummaryResponse(
            q.getId(),
            q.getTitle(),
            q.getDifficulty(),
            q.getTags().stream().map(Tag::getName).collect(Collectors.toSet())
        );
    }
}

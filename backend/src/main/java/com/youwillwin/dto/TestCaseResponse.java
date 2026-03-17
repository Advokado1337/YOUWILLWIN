package com.youwillwin.dto;

import com.youwillwin.model.TestCase;

public record TestCaseResponse(
    Long id,
    String input,
    String expectedOutput,
    boolean sample,
    int orderIndex
) {
    public static TestCaseResponse from(TestCase tc) {
        return new TestCaseResponse(
            tc.getId(),
            tc.getInput(),
            tc.getExpectedOutput(),
            tc.isSample(),
            tc.getOrderIndex()
        );
    }
}

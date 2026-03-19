package com.youwillwin.dto;

import com.youwillwin.model.TestCase;

public record TestCaseResponse(
    Long id,
    String input,
    String expectedOutput,
    String displayInput,
    String displayOutput,
    boolean sample,
    int orderIndex
) {
    public static TestCaseResponse from(TestCase tc) {
        return new TestCaseResponse(
            tc.getId(),
            tc.getInput(),
            tc.getExpectedOutput(),
            tc.getDisplayInput(),
            tc.getDisplayOutput(),
            tc.isSample(),
            tc.getOrderIndex()
        );
    }
}

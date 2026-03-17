package com.youwillwin.dto;

import com.youwillwin.model.TestCase;

public record TestCaseResponse(
    Long id,
    String displayInput,
    String displayOutput,
    boolean sample,
    int orderIndex
) {
    public static TestCaseResponse from(TestCase tc) {
        return new TestCaseResponse(
            tc.getId(),
            tc.getDisplayInput(),
            tc.getDisplayOutput(),
            tc.isSample(),
            tc.getOrderIndex()
        );
    }
}

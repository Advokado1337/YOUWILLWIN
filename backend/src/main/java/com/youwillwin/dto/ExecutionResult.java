package com.youwillwin.dto;

import java.util.List;

public record ExecutionResult(
    String status,
    List<TestCaseResult> testCases,
    String compileError
) {
    public record TestCaseResult(
        int index,
        boolean passed,
        String displayInput,
        String displayOutput,
        String actualOutput
    ) {}
}

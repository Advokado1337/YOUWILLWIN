package com.youwillwin.controller;

import com.youwillwin.dto.AttemptResponse;
import com.youwillwin.dto.ExecutionRequest;
import com.youwillwin.dto.ExecutionResult;
import com.youwillwin.dto.SubmitResponse;
import com.youwillwin.model.Attempt;
import com.youwillwin.model.Question;
import com.youwillwin.model.TestCase;
import com.youwillwin.service.AttemptService;
import com.youwillwin.service.ExecutionService;
import com.youwillwin.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/execute")
public class ExecutionController {

    private final ExecutionService executionService;
    private final QuestionService questionService;
    private final AttemptService attemptService;

    public ExecutionController(ExecutionService executionService,
                               QuestionService questionService,
                               AttemptService attemptService) {
        this.executionService = executionService;
        this.questionService = questionService;
        this.attemptService = attemptService;
    }

    @PostMapping("/run")
    public ExecutionResult run(@Valid @RequestBody ExecutionRequest request) {
        Question question = questionService.findById(request.questionId());
        List<TestCase> sampleCases = question.getTestCases().stream()
            .filter(TestCase::isSample)
            .toList();
        return executionService.execute(request.code(), question.getDriverCode(), sampleCases);
    }

    @PostMapping("/submit")
    public SubmitResponse submit(@Valid @RequestBody ExecutionRequest request) {
        Question question = questionService.findById(request.questionId());
        ExecutionResult result = executionService.execute(request.code(), question.getDriverCode(), question.getTestCases());
        Attempt attempt = attemptService.record(question, request.code(), result, request.timeSpentMs());
        return new SubmitResponse(result, AttemptResponse.from(attempt));
    }
}

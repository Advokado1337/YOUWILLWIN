package com.youwillwin.controller;

import com.youwillwin.dto.QuestionRequest;
import com.youwillwin.dto.QuestionResponse;
import com.youwillwin.dto.QuestionSummaryResponse;
import com.youwillwin.model.Difficulty;
import com.youwillwin.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionSummaryResponse> list(
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String tag) {
        return questionService.findAll(difficulty, tag).stream()
            .map(QuestionSummaryResponse::from)
            .toList();
    }

    @GetMapping("/{id}")
    public QuestionResponse get(@PathVariable Long id) {
        return QuestionResponse.from(questionService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse create(@Valid @RequestBody QuestionRequest request) {
        return QuestionResponse.from(questionService.create(request));
    }

    @PutMapping("/{id}")
    public QuestionResponse update(@PathVariable Long id,
                                   @Valid @RequestBody QuestionRequest request) {
        return QuestionResponse.from(questionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }
}

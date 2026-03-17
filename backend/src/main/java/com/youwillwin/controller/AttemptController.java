package com.youwillwin.controller;

import com.youwillwin.dto.AttemptResponse;
import com.youwillwin.service.AttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @GetMapping
    public List<AttemptResponse> list(@RequestParam(required = false) Long questionId) {
        if (questionId != null) {
            return attemptService.findByQuestionId(questionId).stream()
                .map(AttemptResponse::from)
                .toList();
        }
        return attemptService.findAll().stream()
            .map(AttemptResponse::from)
            .toList();
    }

    @GetMapping("/{id}")
    public AttemptResponse get(@PathVariable Long id) {
        return AttemptResponse.from(attemptService.findById(id));
    }
}

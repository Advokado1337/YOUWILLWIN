package com.youwillwin.service;

import com.youwillwin.dto.ExecutionResult;
import com.youwillwin.model.Attempt;
import com.youwillwin.model.Question;
import com.youwillwin.repository.AttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;

    public AttemptService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    public Attempt record(Question question, String code, ExecutionResult result, Long timeSpentMs) {
        int passedCount = (int) result.testCases().stream().filter(ExecutionResult.TestCaseResult::passed).count();

        Attempt attempt = new Attempt();
        attempt.setQuestion(question);
        attempt.setCode(code);
        attempt.setStatus(result.status());
        attempt.setPassedCount(passedCount);
        attempt.setTotalCount(result.testCases().size());
        attempt.setTimeSpentMs(timeSpentMs);
        return attemptRepository.save(attempt);
    }

    public List<Attempt> findByQuestionId(Long questionId) {
        return attemptRepository.findByQuestionIdOrderByExecutedAtDesc(questionId);
    }

    public List<Attempt> findAll() {
        return attemptRepository.findAllByOrderByExecutedAtDesc();
    }

    public Attempt findById(Long id) {
        return attemptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attempt not found: " + id));
    }
}

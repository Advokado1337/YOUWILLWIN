package com.youwillwin.service;

import com.youwillwin.dto.ActivityResponse;
import com.youwillwin.dto.StatsOverviewResponse;
import com.youwillwin.dto.TagStatsResponse;
import com.youwillwin.repository.AttemptRepository;
import com.youwillwin.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatsService {

    private final AttemptRepository attemptRepository;
    private final QuestionRepository questionRepository;

    public StatsService(AttemptRepository attemptRepository, QuestionRepository questionRepository) {
        this.attemptRepository = attemptRepository;
        this.questionRepository = questionRepository;
    }

    public StatsOverviewResponse getOverview() {
        long totalQuestions = questionRepository.count();
        long totalAttempts = attemptRepository.count();
        long totalSolved = attemptRepository.findSolvedQuestionIds().size();
        long totalPassed = attemptRepository.countByStatus("PASS");
        double solveRate = totalAttempts > 0 ? (double) totalPassed / totalAttempts * 100 : 0;
        return new StatsOverviewResponse(totalQuestions, totalAttempts, totalSolved, Math.round(solveRate * 10) / 10.0);
    }

    public List<TagStatsResponse> getStatsByTag() {
        return attemptRepository.getStatsByTag().stream()
            .map(row -> {
                String name = (String) row[0];
                String type = (String) row[1];
                long totalAttempts = ((Number) row[2]).longValue();
                long passCount = ((Number) row[3]).longValue();
                double passRate = totalAttempts > 0 ? (double) passCount / totalAttempts * 100 : 0;
                return new TagStatsResponse(name, type, totalAttempts, passCount, Math.round(passRate * 10) / 10.0);
            })
            .toList();
    }

    public List<ActivityResponse> getActivity() {
        return attemptRepository.getActivityHistory().stream()
            .map(row -> {
                LocalDate date = ((java.sql.Date) row[0]).toLocalDate();
                long attempts = ((Number) row[1]).longValue();
                long passed = ((Number) row[2]).longValue();
                return new ActivityResponse(date, attempts, passed);
            })
            .toList();
    }
}

package com.youwillwin.dto;

public record StatsOverviewResponse(
    long totalQuestions,
    long totalAttempts,
    long totalSolved,
    double solveRate
) {}

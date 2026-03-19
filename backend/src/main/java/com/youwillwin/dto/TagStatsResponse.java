package com.youwillwin.dto;

public record TagStatsResponse(
    String tagName,
    String tagType,
    long totalAttempts,
    long passCount,
    double passRate
) {}

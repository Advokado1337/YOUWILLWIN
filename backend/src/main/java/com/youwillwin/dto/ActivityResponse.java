package com.youwillwin.dto;

import java.time.LocalDate;

public record ActivityResponse(
    LocalDate date,
    long attempts,
    long passed
) {}

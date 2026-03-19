package com.youwillwin.controller;

import com.youwillwin.dto.ActivityResponse;
import com.youwillwin.dto.StatsOverviewResponse;
import com.youwillwin.dto.TagStatsResponse;
import com.youwillwin.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/overview")
    public StatsOverviewResponse overview() {
        return statsService.getOverview();
    }

    @GetMapping("/by-tag")
    public List<TagStatsResponse> byTag() {
        return statsService.getStatsByTag();
    }

    @GetMapping("/activity")
    public List<ActivityResponse> activity() {
        return statsService.getActivity();
    }
}

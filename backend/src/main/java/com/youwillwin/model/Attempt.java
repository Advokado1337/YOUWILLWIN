package com.youwillwin.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "passed_count", nullable = false)
    private int passedCount;

    @Column(name = "total_count", nullable = false)
    private int totalCount;

    @Column(name = "time_spent_ms")
    private Long timeSpentMs;

    @Column(name = "executed_at", nullable = false)
    private LocalDateTime executedAt;

    @PrePersist
    protected void onCreate() {
        executedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getPassedCount() { return passedCount; }
    public void setPassedCount(int passedCount) { this.passedCount = passedCount; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public Long getTimeSpentMs() { return timeSpentMs; }
    public void setTimeSpentMs(Long timeSpentMs) { this.timeSpentMs = timeSpentMs; }

    public LocalDateTime getExecutedAt() { return executedAt; }
}

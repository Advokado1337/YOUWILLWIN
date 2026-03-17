package com.youwillwin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String input;

    @Column(name = "expected_output", nullable = false, columnDefinition = "TEXT")
    private String expectedOutput;

    @Column(name = "is_sample", nullable = false)
    private boolean sample = true;

    @Column(name = "order_index", nullable = false)
    private int orderIndex = 0;

    @Column(name = "display_input", columnDefinition = "TEXT")
    private String displayInput;

    @Column(name = "display_output", columnDefinition = "TEXT")
    private String displayOutput;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }

    public String getExpectedOutput() { return expectedOutput; }
    public void setExpectedOutput(String expectedOutput) { this.expectedOutput = expectedOutput; }

    public boolean isSample() { return sample; }
    public void setSample(boolean sample) { this.sample = sample; }

    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }

    public String getDisplayInput() { return displayInput; }
    public void setDisplayInput(String displayInput) { this.displayInput = displayInput; }

    public String getDisplayOutput() { return displayOutput; }
    public void setDisplayOutput(String displayOutput) { this.displayOutput = displayOutput; }
}

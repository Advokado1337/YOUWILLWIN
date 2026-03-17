package com.youwillwin.repository;

import com.youwillwin.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    List<Attempt> findByQuestionIdOrderByExecutedAtDesc(Long questionId);

    List<Attempt> findAllByOrderByExecutedAtDesc();
}

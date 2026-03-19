package com.youwillwin.repository;

import com.youwillwin.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    List<Attempt> findByQuestionIdOrderByExecutedAtDesc(Long questionId);

    List<Attempt> findAllByOrderByExecutedAtDesc();

    long countByStatus(String status);

    @Query("SELECT DISTINCT a.question.id FROM Attempt a WHERE a.status = 'PASS'")
    List<Long> findSolvedQuestionIds();

    @Query(value = """
        SELECT t.name, t.type,
               COUNT(a.id) as total_attempts,
               SUM(CASE WHEN a.status = 'PASS' THEN 1 ELSE 0 END) as pass_count
        FROM attempts a
        JOIN questions q ON a.question_id = q.id
        JOIN question_tags qt ON q.id = qt.question_id
        JOIN tags t ON qt.tag_id = t.id
        GROUP BY t.name, t.type
        ORDER BY total_attempts DESC
        """, nativeQuery = true)
    List<Object[]> getStatsByTag();

    @Query(value = """
        SELECT CAST(a.executed_at AS DATE) as day,
               COUNT(a.id) as attempts,
               SUM(CASE WHEN a.status = 'PASS' THEN 1 ELSE 0 END) as passed
        FROM attempts a
        GROUP BY CAST(a.executed_at AS DATE)
        ORDER BY day DESC
        LIMIT 30
        """, nativeQuery = true)
    List<Object[]> getActivityHistory();
}

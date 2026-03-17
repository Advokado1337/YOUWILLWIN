package com.youwillwin.repository;

import com.youwillwin.model.Difficulty;
import com.youwillwin.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.tags")
    List<Question> findAllWithTags();

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.testCases WHERE q IN :questions")
    List<Question> fetchTestCases(@Param("questions") List<Question> questions);

    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.tags WHERE q.id = :id")
    Optional<Question> findByIdWithTags(@Param("id") Long id);

    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.testCases WHERE q.id = :id")
    Optional<Question> findByIdWithTestCases(@Param("id") Long id);

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.tags " +
           "WHERE q.difficulty = :difficulty")
    List<Question> findByDifficulty(@Param("difficulty") Difficulty difficulty);

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.tags t " +
           "WHERE t.name = :tagName")
    List<Question> findByTagName(@Param("tagName") String tagName);

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.tags t " +
           "WHERE (:difficulty IS NULL OR q.difficulty = :difficulty) " +
           "AND (:tagName IS NULL OR t.name = :tagName)")
    List<Question> findByFilters(@Param("difficulty") Difficulty difficulty,
                                 @Param("tagName") String tagName);
}

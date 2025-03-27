package com.codeine.smartquizmaster.repository;

import com.codeine.smartquizmaster.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    void deleteQuizResultByQuizId(Long quizId);
}

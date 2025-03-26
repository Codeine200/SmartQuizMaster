package com.codeine.smartquizmaster.repository;

import com.codeine.smartquizmaster.entity.Quiz;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Quiz q SET q.name = :name, q.description = :description WHERE q.id = :quizId")
    int update(@Param("quizId") Long quizId, @Param("name") String name, @Param("description") String description);

}
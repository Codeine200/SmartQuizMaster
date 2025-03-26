package com.codeine.smartquizmaster.service;

import com.codeine.smartquizmaster.entity.Quiz;
import com.codeine.smartquizmaster.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    public void save(Quiz quiz) {
        if (quiz.getId() != null) {
            quizRepository.update(quiz.getId(), quiz.getName(), quiz.getDescription());
        } else {
            quizRepository.save(quiz);
        }
    }

    public void delete(Long quizId) {
        quizRepository.deleteById(quizId);
    }
}

package com.codeine.smartquizmaster.service;

import com.codeine.smartquizmaster.entity.Answer;
import com.codeine.smartquizmaster.entity.Quiz;
import com.codeine.smartquizmaster.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Optional<Answer> findById(Long id) {
        return  answerRepository.findById(id);
    }

    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }
}

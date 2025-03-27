package com.codeine.smartquizmaster.service;

import com.codeine.smartquizmaster.entity.Answer;
import com.codeine.smartquizmaster.entity.Question;
import com.codeine.smartquizmaster.model.QuestionType;
import com.codeine.smartquizmaster.repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.codeine.smartquizmaster.repository.QuestionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> findAllQuestionsByQuizId(Long quizId) {
        return questionRepository.findAllQuestionsByQuizId(quizId);
    }

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Transactional
    public Question save(Question question) {
        if (question.getType().in(QuestionType.SINGLE, QuestionType.MULTIPLE)) {
            if (!CollectionUtils.isEmpty(question.getAnswers())) {
                List<Answer> answers = question.getAnswers().stream().filter(a -> a.getName() != null).toList();
                answers.forEach(a -> a.setQuestion(question));
                question.setAnswers(answers);
            }
            questionRepository.save(question);
        } else {
            question.setAnswers(null);
            questionRepository.save(question);
        }
        return question;
    }

    public void deleteById(Long quizId) {
        questionRepository.deleteById(quizId);
    }
}

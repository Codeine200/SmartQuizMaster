package com.codeine.smartquizmaster.service;

import com.codeine.smartquizmaster.entity.Answer;
import com.codeine.smartquizmaster.entity.Quiz;
import com.codeine.smartquizmaster.entity.QuizResult;
import com.codeine.smartquizmaster.model.QuestionType;
import com.codeine.smartquizmaster.repository.QuestionRepository;
import com.codeine.smartquizmaster.repository.QuizResultRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final QuestionRepository questionRepository;

    public void deleteById(Long id) {
        quizResultRepository.deleteById(id);
    }
    public void deleteByQuizId(Long id) {
        quizResultRepository.deleteQuizResultByQuizId(id);
    }

    public QuizResult save(QuizResult result) {
        return quizResultRepository.save(result);
    }

    @Transactional
    public void save(Long quizId, MultiValueMap<String, String> answers) {
        deleteByQuizId(quizId);
        if (answers != null) {
            Quiz quiz = new Quiz();
            quiz.setId(quizId);
            answers.forEach((questionId, answerValues) -> {
                long idQuestion = getIdFromString(questionId);
                if (idQuestion != -1) {
                    questionRepository.findById(idQuestion)
                            .ifPresent(q -> answerValues.forEach(answerId -> {
                                QuizResult.QuizResultBuilder builder = QuizResult.builder()
                                        .quiz(quiz)
                                        .question(q);
                                if (q.getType() == QuestionType.TEXT) {
                                    builder.answerText(answerId);
                                } else {
                                    Answer answer = new Answer();
                                    answer.setId(Long.parseLong(answerId));
                                    builder.answer(answer);
                                }
                                quizResultRepository.save(builder.build());
                            }));
                }
            });

        }
    }

    private long getIdFromString(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return Long.parseLong(matcher.group());
        }
        return -1;
    }
}

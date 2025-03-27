package com.codeine.smartquizmaster.controller;

import com.codeine.smartquizmaster.entity.Question;
import com.codeine.smartquizmaster.service.QuestionService;
import com.codeine.smartquizmaster.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuizService quizService;

    @GetMapping
    public String list(@RequestParam("quizId") Long quizId, Model model) {
        return quizService.findById(quizId)
                .map(q -> {
                    List<Question> questions = questionService.findAllQuestionsByQuizId(quizId);

                    model.addAttribute("quiz", q);
                    model.addAttribute("questions", questions);
                    return "question/list";
                }).orElse("redirect:/quiz");
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Question> question = questionService.findById(id);
        model.addAttribute("question", question);
        return "question/form";
    }

    @GetMapping("/new")
    public String showAddForm(@RequestParam("quizId") Long quizId, Model model) {
        return quizService.findById(quizId)
                .map(q -> {
                    Question question = new Question();
                    question.setQuiz(q);
                    model.addAttribute("question", question);
                    model.addAttribute("answersSize", 0);
                    return "question/form";
                }).orElse("redirect:/quiz");

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        return questionService.findById(id)
                .map(q -> {
                    model.addAttribute("question", q);
                    model.addAttribute("answersSize", q.getAnswers().size());
                    return "question/form";
                }).orElse("redirect:/new");
    }

    @PostMapping
    public String save(@ModelAttribute Question question) {
        questionService.save(question);
        return "redirect:/quiz/" + question.getQuiz().getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam("quizId") Long quizId) {
        questionService.deleteById(id);
        return "redirect:/quiz/" + quizId;
    }
}
package com.codeine.smartquizmaster.controller;

import com.codeine.smartquizmaster.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/quiz")
@AllArgsConstructor
public class FrontController {

    private final QuizService quizService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "front/quiz/list";
    }

    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return quizService.findById(id)
                .map(q -> {
                    model.addAttribute("quiz", q);
                    return "front/quiz/quiz";
                }).orElse("redirect:/front/quiz");
    }
}

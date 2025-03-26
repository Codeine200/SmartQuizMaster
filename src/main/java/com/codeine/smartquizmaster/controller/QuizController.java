package com.codeine.smartquizmaster.controller;

import com.codeine.smartquizmaster.dto.request.QuizRequest;
import com.codeine.smartquizmaster.entity.Quiz;
import com.codeine.smartquizmaster.mapper.QuizMapper;
import com.codeine.smartquizmaster.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "quiz/list";
    }

    @PostMapping
    public String save(@ModelAttribute Quiz quiz) {
        quizService.save(quiz);
        return "redirect:quiz";
    }

    @GetMapping("/{id}")
    public String detailed(@PathVariable Long id, Model model) {
        return quizService.findById(id)
                .map(q -> {
                    model.addAttribute("quiz", q);
                    return "quiz/detailed";
                }).orElse("redirect:/quiz");
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("quiz", new QuizRequest());
        return "quiz/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return quizService.findById(id)
                .map(q -> {
                    model.addAttribute("quiz", q);
                    return "quiz/form";
                }).orElse("redirect:/new");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        quizService.delete(id);
        return "redirect:/quiz";
    }
}

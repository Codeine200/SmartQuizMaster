package com.codeine.smartquizmaster.controller;

import com.codeine.smartquizmaster.entity.Answer;
import com.codeine.smartquizmaster.entity.Question;
import com.codeine.smartquizmaster.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam("quizId") Long quizId) {
        answerService.deleteById(id);
        return "redirect:/quiz/" + quizId;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @RequestParam("quizId") Long quizId, Model model) {
        return answerService.findById(id)
                .map(a -> {
                    model.addAttribute("answer", a);
                    model.addAttribute("quizId", quizId);
                    return "answer/form";
                }).orElse("redirect:/quiz/" + quizId);
    }

    @PostMapping
    public String save(@ModelAttribute Answer answer, @RequestParam("quizId") Long quizId) {
        answerService.save(answer);
        return "redirect:/quiz/" + quizId;
    }

}

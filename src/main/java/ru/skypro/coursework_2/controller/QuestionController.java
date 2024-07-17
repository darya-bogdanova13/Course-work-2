package ru.skypro.coursework_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.coursework_2.model.Question;
import ru.skypro.coursework_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class QuestionController {
    private final QuestionService questionService;


    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(String question, String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(String question, String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getQuestion() {
        return questionService.findAll();
    }

}

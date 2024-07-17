package ru.skypro.coursework_2.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.coursework_2.exception.NumberOfQuestionsExceededException;
import ru.skypro.coursework_2.model.Question;
import ru.skypro.coursework_2.service.ExaminerService;
import ru.skypro.coursework_2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {

        int size = questionService.findAll().size();
        if (amount > size) {
            throw new NumberOfQuestionsExceededException("Запросили " + amount + "вопросов, доступно - " + "вопросов");
        }
        Set<Question> questions = new HashSet<>();

        while (questions.size() < amount) {
            questions.add(questionService.findAllRandomQuestion());
        }
        return questions;
    }
}
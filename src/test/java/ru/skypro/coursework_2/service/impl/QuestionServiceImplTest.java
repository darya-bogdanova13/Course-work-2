package ru.skypro.coursework_2.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.coursework_2.model.Question;
import ru.skypro.coursework_2.service.QuestionService;

import java.util.Collection;

import java.util.Set;

public class QuestionServiceImplTest {
    private QuestionService questionService;

    @BeforeEach
    public void clear() {
        questionService = new QuestionServiceImpl();
    }

    @Test
    public void shouldCorrectlyAddQuestions() {
        Question expectedQuestion = new Question("question", "answer");

        Question actualQuestion1 = questionService.add(expectedQuestion.getQuestion(), expectedQuestion.getAnswer());

        Question actualQuestion2 = questionService.add(expectedQuestion);

        Assertions.assertEquals(expectedQuestion.getQuestion(), actualQuestion1.getQuestion());
        Assertions.assertEquals(expectedQuestion.getAnswer(), actualQuestion1.getAnswer());

        Assertions.assertEquals(expectedQuestion, actualQuestion2);
    }

    @Test
    public void shouldRemoveQuestion() {

        Question question = new Question("question", "answer");
        questionService.add(question);

        Question actualQuestion = questionService.remove(question);

        Assertions.assertEquals(question, actualQuestion);
    }

    @Test
    public void findAll() {

        Question question1 = new Question("question", "answer");

        Question question2 = new Question("question2", "answer2");

        questionService.add(question1);
        questionService.add(question2);

        Collection<Question> actualQuestions = questionService.findAll();

        Assertions.assertEquals(Set.of(question1, question2), actualQuestions);
    }

    @Test
    public void findAllRandomQuestion() {

        Question question1 = new Question("question", "answer");

        Question question2 = new Question("question2", "answer2");

        questionService.add(question1);
        questionService.add(question2);

        Question randomQuestion = questionService.findAllRandomQuestion();

        Assertions.assertTrue(Set.of(question1, question2).contains(randomQuestion));
    }
}

package ru.skypro.coursework_2.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import ru.skypro.coursework_2.exception.NumberOfQuestionsExceededException;
import ru.skypro.coursework_2.model.Question;
import ru.skypro.coursework_2.service.QuestionService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void shouldCollect() {

        int amount = 2;
        List<Question> questionList = List.of(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        );

        Mockito.when(questionService.findAll()).thenReturn(questionList);
        Mockito.when(questionService.findAllRandomQuestion()).thenReturn(
                questionList.get(0),
                questionList.get(0),
                questionList.get(0),
                questionList.get(1)
        );

        Collection<Question> actualQuestions = examinerService.getQuestion(amount);

        Assertions.assertEquals(Set.of(questionList.get(0), questionList.get(1)), actualQuestions);

        Mockito.verify(questionService, times(4)).findAllRandomQuestion();
    }

    @Test
    void shouldThrowEnoughQuestions() {
        int amount = 5;
        List<Question> questionList = List.of(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        );

        Mockito.when(questionService.findAll()).thenReturn(questionList);

        Assertions.assertThrows(
                NumberOfQuestionsExceededException.class,
                () -> examinerService.getQuestion(amount)
        );
    }
}
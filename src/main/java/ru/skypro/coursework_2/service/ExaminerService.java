package ru.skypro.coursework_2.service;

import ru.skypro.coursework_2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestion(int amount);

}

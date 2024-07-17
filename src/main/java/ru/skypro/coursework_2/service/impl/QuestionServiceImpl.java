package ru.skypro.coursework_2.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.skypro.coursework_2.model.Question;
import ru.skypro.coursework_2.service.QuestionService;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        add("Какие циклы вы знаете?", "for, while, do-while.");
        add("Какие условные операторы вы знаете?", "if, else.");
        add("Что такое инкапсуляция?", "Инкапсуляция в Java подразумевает разграничение доступа к данным и возможностям классов и объектов.");
        add("Методы каких типов бывают?", "Статические и нестатические.");
        add("В чём различия переменных примитивного и ссылочного типа?", "Примитивы хранят данные, а переменные ссылочного типа хранят ссылки на данные из области памяти.");
    }
    @Override
    public Question add(String question, String answer) {
        Question questionAdd = new Question(question, answer);
        questions.add(questionAdd);
        return questionAdd;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> findAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question findAllRandomQuestion() {
        int randomQuestion = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(randomQuestion);
    }
}

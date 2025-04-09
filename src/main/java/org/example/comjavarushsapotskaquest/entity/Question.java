package org.example.comjavarushsapotskaquest.entity;

import java.util.List;

public class Question {
    private Long id;
    private String question;
    private List<Answer> answers;

    public Question(Long id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}

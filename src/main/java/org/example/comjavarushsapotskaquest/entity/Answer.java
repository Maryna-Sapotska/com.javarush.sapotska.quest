package org.example.comjavarushsapotskaquest.entity;

public class Answer {
    private Long id;
    private String answer;
    private Long nextQuestionId;
    private boolean isGameOver;
    private boolean isVictory;
    private boolean isDefeat;

    public Answer(Long id, String answer, Long nextQuestionId, boolean isGameOver, boolean isVictory, boolean isDefeat) {
        this.id = id;
        this.answer = answer;
        this.nextQuestionId = nextQuestionId;
        this.isGameOver = isGameOver;
        this.isVictory = isVictory;
        this.isDefeat = isDefeat;
    }

    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public Long getNextQuestionId() {
        return nextQuestionId;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isVictory() {
        return isVictory;
    }

    public boolean isDefeat() {
        return isDefeat;
    }
}

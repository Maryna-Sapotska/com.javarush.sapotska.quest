package org.example.comjavarushsapotskaquest.entity;

public class Game {
    private String playerName;
    private Long currentQuestionId;
    private boolean isGameOver;
    private boolean isVictory;
    private boolean isDefeat;


    public Game(String playerName) {
        this.playerName = playerName;
        this.currentQuestionId = 1L;
        this.isGameOver = false;
        this.isVictory = false;
        this.isDefeat = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Long getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(Long currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isVictory() {
        return isVictory;
    }

    public void setVictory(boolean victory) {
        this.isVictory = victory;
        this.isGameOver = true;
    }

    public boolean isDefeat() {
        return isDefeat;
    }

    public void setDefeat(boolean defeat) {
        this.isDefeat = defeat;
        this.isGameOver = true;
    }
}
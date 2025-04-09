package org.example.comjavarushsapotskaquest.service;

import org.example.comjavarushsapotskaquest.entity.Answer;
import org.example.comjavarushsapotskaquest.entity.Game;
import org.example.comjavarushsapotskaquest.entity.Question;
import org.example.comjavarushsapotskaquest.repository.GameRepository;

public class GameService {
    private GameRepository gameRepository;
    private Game game;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void startNewGame(String playerName) {
        this.game = new Game(playerName);
    }

    public Game getGame() {
        return game;
    }

    public Question getCurrentQuestion() {
        if (game == null) {
            throw new IllegalStateException("Игра не была начата");
        }
        return gameRepository.getQuestionById(game.getCurrentQuestionId());
    }

    public void answerQuestion(Long answerId) {
        if (game == null) {
            throw new IllegalStateException("Игра не была начата");
        }
        Question currentQuestion = gameRepository.getQuestionById(game.getCurrentQuestionId());
        Answer selectedAnswer = currentQuestion
                .getAnswers()
                .stream()
                .filter(answer -> answer.getId().equals(answerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Некорректный ответ"));

        if (selectedAnswer.isGameOver()) {
            if (selectedAnswer.isVictory()) {
                game.setVictory(true);
            } else if (selectedAnswer.isDefeat()) {
                game.setDefeat(true);
            }
            else {
                game.setGameOver(true);
            }
        } else {
            game.setCurrentQuestionId(selectedAnswer.getNextQuestionId());
        }
    }

    public boolean isGameOver() {
        if (game == null) {
            throw new IllegalStateException("Игра не была начата");
        }
        return game.isGameOver();
    }

    public boolean isVictory() {
        return game.isVictory();
    }

    public boolean isDefeat() {
        return game.isDefeat();
    }
}
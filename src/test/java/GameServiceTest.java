import org.example.comjavarushsapotskaquest.entity.Answer;
import org.example.comjavarushsapotskaquest.entity.Question;
import org.example.comjavarushsapotskaquest.repository.GameRepository;
import org.example.comjavarushsapotskaquest.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(gameRepository);
    }

    @Test
    public void testStartNewGame() {
        gameService.startNewGame("Player1");
        assertNotNull(gameService.getGame(), "Game should be initialized");
        assertEquals("Player1", gameService.getGame().getPlayerName(), "Player name should be Player1");
    }

    @Test
    public void testGetCurrentQuestion() {
        Question mockQuestion = new Question(1L, "What do you want to do?", List.of());
        when(gameRepository.getQuestionById(anyLong())).thenReturn(mockQuestion);

        gameService.startNewGame("Player1");
        Question currentQuestion = gameService.getCurrentQuestion();

        assertNotNull(currentQuestion, "Current question should not be null");
        assertEquals("What do you want to do?", currentQuestion.getQuestion(), "Question text should match");
    }

    @Test
    public void testAnswerQuestionVictory() {
        // Мокирую вопрос и ответ
        Question mockQuestion = new Question(1L, "Choose your path", List.of(
                new Answer(2L, "Go to the city", 3L, true, true, false)
        ));

        when(gameRepository.getQuestionById(1L)).thenReturn(mockQuestion);

        gameService.startNewGame("Player1");
        gameService.answerQuestion(2L);

        assertTrue(gameService.isVictory(), "Game shouldn't be won");
    }

    @Test
    public void testAnswerQuestionDefeat() {
        Question mockQuestion = new Question(1L, "Choose your path", List.of(
                new Answer(1L, "Go back", 2L, true, false, true)
        ));

        when(gameRepository.getQuestionById(1L)).thenReturn(mockQuestion);

        gameService.startNewGame("Player1");
        gameService.answerQuestion(1L);

        assertTrue(gameService.isDefeat(), "Game should be lost");
    }

    @Test
    public void testGameOver() {
        Question mockQuestion = new Question(1L, "End of the road", List.of(
                new Answer(3L, "Finish", 0L, true, false, true)
        ));

        when(gameRepository.getQuestionById(1L)).thenReturn(mockQuestion);

        gameService.startNewGame("Player1");
        gameService.answerQuestion(3L);

        assertTrue(gameService.isGameOver(), "Game should be over");
    }
}

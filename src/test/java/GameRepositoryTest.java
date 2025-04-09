import org.example.comjavarushsapotskaquest.entity.Answer;
import org.example.comjavarushsapotskaquest.entity.Question;
import org.example.comjavarushsapotskaquest.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameRepositoryTest {
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        gameRepository = new GameRepository();
    }

    @Test
    public void testGetQuestionById() {
        Question question = gameRepository.getQuestionById(1L);
        assertNotNull(question, "Question should not be null");
        assertEquals(1L, question.getId(), "Question ID should be 1");
    }

    @Test
    public void testGetAnswersForQuestion() {
        Question question = gameRepository.getQuestionById(1L);
        List<Answer> answers = question.getAnswers();
        assertNotNull(answers, "Answers list should not be null");
        assertTrue(answers.size() > 0, "Answers list should not be empty");
    }
}


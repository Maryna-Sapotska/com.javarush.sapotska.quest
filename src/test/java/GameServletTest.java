import org.example.comjavarushsapotskaquest.controller.GameServlet;
import org.example.comjavarushsapotskaquest.entity.Question;
import org.example.comjavarushsapotskaquest.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class GameServletTest {
    private GameServlet gameServlet;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private GameService gameService;
    @Mock private HttpSession session;
    @Mock private Question question;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameServlet = new GameServlet();
        gameServlet.gameService = gameService;
    }

    @Test
    public void testDoGet_playerNameNotInSession_redirectsToIndex() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn(null);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoGet_gameOver_redirectsToEndPage() throws Exception {
        String playerName = "player1";
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("playerName")).thenReturn(playerName);
        when(gameService.isGameOver()).thenReturn(true);
        when(gameService.isVictory()).thenReturn(true);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        gameServlet.doGet(request, response);

        verify(request).setAttribute("message", "Поздравляем, вы победили!");
        verify(request).getRequestDispatcher("index.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_playerNameProvided_startsNewGame() throws Exception {
        String playerName = "player1";
        when(request.getParameter("playerName")).thenReturn(playerName);
        when(request.getSession()).thenReturn(session);

        gameServlet.doPost(request, response);

        verify(session).setAttribute("playerName", playerName);
        verify(gameService).startNewGame(playerName);
        verify(response).sendRedirect("game-servlet");
    }

    @Test
    public void testDoPost_invalidAnswerId_showsError() throws Exception {
        when(request.getParameter("answerId")).thenReturn("invalid");

        gameServlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid answerId format.");
    }

    @Test
    public void testDoPost_missingAnswerId_showsError() throws Exception {
        when(request.getParameter("answerId")).thenReturn(null);

        gameServlet.doPost(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing answerId.");
    }
}
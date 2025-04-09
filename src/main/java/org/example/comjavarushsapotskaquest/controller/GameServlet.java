package org.example.comjavarushsapotskaquest.controller;

import org.example.comjavarushsapotskaquest.entity.Question;
import org.example.comjavarushsapotskaquest.repository.GameRepository;
import org.example.comjavarushsapotskaquest.service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "gameServlet", value = "/game-servlet")
public class GameServlet extends HttpServlet {
    public GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        GameRepository gameRepository = new GameRepository();
        gameService = new GameService(gameRepository);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if (session.getAttribute("playerName") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String playerName = (String) session.getAttribute("playerName");
        request.setAttribute("playerName", playerName);

        // Если игра не была начата, начинаем новую игру
        if (gameService.isGameOver()) {
            if (gameService.isVictory()) {
                request.setAttribute("message", "Поздравляем, вы победили!");
                request.setAttribute("playerName", playerName);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else if (gameService.isDefeat()) {
                request.setAttribute("message", "К сожалению, вы проиграли. Попробуйте снова!");
            }
            else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            return;
        }

        if (gameService.getGame() == null) {
            gameService.startNewGame(playerName);
        }

        Question currentQuestion = gameService.getCurrentQuestion();

        request.setAttribute("question", currentQuestion);
        request.setAttribute("answers", currentQuestion.getAnswers());
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String playerName = request.getParameter("playerName");

        if (playerName != null && !playerName.trim().isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("playerName", playerName);
            gameService.startNewGame(playerName);

            response.sendRedirect("game-servlet");
            return;
        }

        String answerIdStr = request.getParameter("answerId");
        if (answerIdStr != null && !answerIdStr.trim().isEmpty()) {
            try {
                Long answerId = Long.valueOf(answerIdStr);
                gameService.answerQuestion(answerId);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid answerId format.");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing answerId.");
            return;
        }

        if (gameService.isGameOver()) {
            String message = gameService.getGame().isVictory()
                    ? " Поздравляем, вы победили!"
                    : " К сожалению, вы проиграли. Попробуйте снова!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("end.jsp").forward(request, response);
        } else {
            response.sendRedirect("game-servlet");
        }
    }
}
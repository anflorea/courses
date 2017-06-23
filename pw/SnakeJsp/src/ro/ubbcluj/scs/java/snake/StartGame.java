package ro.ubbcluj.scs.java.snake;

import ro.ubbcluj.scs.java.snake.Utils.GameManager;
import ro.ubbcluj.scs.java.snake.model.Game;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by flo on 04/06/2017.
 */
public class StartGame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String direction = request.getParameter("direction");

        PrintWriter out = response.getWriter();

        GameManager gm = GameManager.getInstance();
        Game currentGame = gm.getGameState(userId);

        try {
            switch (direction) {
                case "up":
                    currentGame.moveUp();
                    break;
                case "down":
                    currentGame.moveDown();
                    break;
                case "left":
                    currentGame.moveLeft();
                    break;
                case "right":
                    currentGame.moveRight();
                    break;
            }
        } catch (Exception e) {
            gm.gameOver(out, userId, e.getMessage());
            return ;
        }

        gm.saveGameState(userId, currentGame);
        gm.drawGame(out, currentGame);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            session.setAttribute("error", "Please login first");
            response.sendRedirect("login.jsp");
            return ;
        }

        PrintWriter out = response.getWriter();
        GameManager gm = GameManager.getInstance();
        Game currentGame = gm.createGame((int) session.getAttribute("userId"));

        gm.drawGame(out, currentGame);
    }
}

package ro.ubbcluj.scs.java.snake.Utils;

import ro.ubbcluj.scs.java.snake.model.Game;

import java.io.*;
import java.sql.*;

/**
 * Created by flo on 04/06/2017.
 */
public class GameManager {
    private static GameManager ourInstance = new GameManager();

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
    }

    public void drawGame(PrintWriter out, Game game) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Snake</title>");
        out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"res/style.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"/game\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"direction\" value=\"up\">\n" +
                "<input type=\"submit\" value=\"Up\">\n" +
                "</form> <br>");
        out.println("<form action=\"/game\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"direction\" value=\"down\">\n" +
                "<input type=\"submit\" value=\"Down\">\n" +
                "</form> <br>");
        out.println("<form action=\"/game\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"direction\" value=\"left\">\n" +
                "<input type=\"submit\" value=\"Left\">\n" +
                "</form> <br>");
        out.println("<form action=\"/game\" method=\"post\">\n" +
                "<input type=\"hidden\" name=\"direction\" value=\"right\">\n" +
                "<input type=\"submit\" value=\"Right\">\n" +
                "</form>");
        out.println("<table class=\"board-game\">");

        int[][] matrix = game.getMatrix();
        for (int i = 0; i < game.getSize(); i++) {
            out.println("<tr>");
            for (int j = 0; j < game.getSize(); j++) {
                out.print("<td class=\"dot ");
                if (matrix[i][j] == -1)
                    out.print("wall");
                else if (matrix[i][j] == 0)
                    out.print("empty");
                else if (j == game.getHeadX() && i == game.getHeadY())
                    out.print("head");
                else if (matrix[i][j] == -2)
                    out.print("treat");
                else
                    out.print("body");
                out.println("\"></td>");
            }
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    // TODO: show the time spent in the game
    public void gameOver(PrintWriter out, int userId, String message) {

        long timeSpent = 0;
        try {
            // Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            PreparedStatement pstmt = conn.prepareStatement("SELECT started_at FROM game WHERE userid=?");

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Timestamp ts = rs.getTimestamp(1);
                timeSpent = System.currentTimeMillis() - ts.getTime();
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Game over</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Game over <br>");
        out.println(message);
        out.println("<br> You spent: " + (timeSpent / 1000.0) + " seconds in game");
        out.println("<br> <a href='index.jsp'>Go back home</a>");
        out.println("</body>");
        out.println("</html>");
    }

    public Game createGame(int userId) {
        Game game = new Game();

        try {
            // Serialize the game to save into Blob object
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(game);
            byte[] gameAsBytes = baos.toByteArray();

            // Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO game (userid, gamestate) VALUES (?, ?)");

            pstmt.setInt(1, userId);

            ByteArrayInputStream bais = new ByteArrayInputStream(gameAsBytes);

            pstmt.setBinaryStream(2, bais, gameAsBytes.length);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public void saveGameState(int userId, Game game) {
        try {
            // Serialize the game to save into Blob object
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(game);
            byte[] gameAsBytes = baos.toByteArray();

            // Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            String updateQuery = "UPDATE game SET gamestate=? WHERE userid=?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);

            pstmt.setInt(2, userId);

            ByteArrayInputStream bais = new ByteArrayInputStream(gameAsBytes);

            pstmt.setBinaryStream(1, bais, gameAsBytes.length);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGameState(int userId) {
        Game theGame = null;
        try {
            // Register JDBC driver
            Class.forName(Constants.JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

            // Execute SQL query
            PreparedStatement pstmt = conn.prepareStatement("SELECT gamestate FROM game WHERE userid=?");

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                byte[] st = (byte[]) rs.getObject(1);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                theGame = (Game) ois.readObject();
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theGame;
    }
}

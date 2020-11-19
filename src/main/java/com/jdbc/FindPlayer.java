package com.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindPlayer extends DataBaseConst {


    public ResultSet getScores() {

        try {
            connectToDb();
            String query = "SELECT coalesce(Score, 0) Score, loginPlayer AS Login, namePlayer AS Name FROM Player LEFT JOIN Score ON Player.id_player = Score.id_player ORDER BY score DESC;";
            ResultSet rs = stmt.executeQuery(query);
            disconnectDB();
            return rs;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean returnPlayer(String login) {

        try {
            connectToDb();
            String query = "SELECT * FROM Player WHERE loginPlayer='" + login + "';";
            ResultSet rs = stmt.executeQuery(query);
            disconnectDB();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean signInPlayer(String login, char[] password) {
        try {
            ArrayList<Character> charListPassword = new ArrayList<Character>();

            for (char c : password) {
                charListPassword.add(c);
            }

            connectToDb();
            Array array = conn.createArrayOf("varchar", charListPassword.toArray());
            String query = "SELECT * FROM Player WHERE loginPlayer='" + login + "' AND passwordplayer='" + array + "';";
            ResultSet rs = stmt.executeQuery(query);
            disconnectDB();
            return rs.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePlayerScore(String login, int score) {
        try {
            if (!login.equals("")) {

                connectToDb();
                String query = "SELECT id_player FROM Player WHERE loginplayer ='" + login + "';";
                ResultSet rs = stmt.executeQuery(query);
                int id = 0;

                while (rs.next()) {
                    id = rs.getInt("id_player");
                    System.out.println("id: " + id);
                }

                prepStmt = conn.prepareStatement("INSERT INTO Score (id_player, score) VALUES (?, ?)");
                prepStmt.setInt(1, id);
                prepStmt.setInt(2, score);
                prepStmt.executeUpdate();
                System.out.println("dodano do id: " + id + ", " + score + "punktów");
                disconnectDB();
            } else
                System.out.println("Grasz niezalogowany");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

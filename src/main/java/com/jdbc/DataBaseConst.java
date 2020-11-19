package com.jdbc;

import java.sql.*;

public abstract class DataBaseConst {

    protected static final String JDBC_DRIVER = "org.postgresql.Driver";
    protected static final String URL = "jdbc:postgresql://localhost/SnakeADV";
    protected static final String LOGIN = "postgres";
    protected static final String PASSWORD = "Trib1pn23#";

    protected Statement stmt;
    protected Connection conn;
    protected PreparedStatement prepStmt;

    protected void showTable(String tableName) {
        String query = "SELECT * FROM " + tableName + ";";
        try {
            connectToDb();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCounter = rsmd.getColumnCount();
                StringBuilder result = new StringBuilder();

                for (int i = 1; i <= columnCounter; i++) {
                    result = result.append(rsmd.getColumnName(i) + ": \u001B[33m" + rs.getString(i) + "\u001B[0m \t");
                }
                result.append("\n");
                System.out.println(result.toString());
            }
            disconnectDB();

        } catch (ClassNotFoundException e) {
            System.err.println("Driver error: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    protected void connectToDb() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        stmt = conn.createStatement();
    }

    protected void disconnectDB() throws SQLException {
        stmt.close();
        conn.close();
    }

    protected int returnNextID() {
        int nextID = -1;
        try {
            connectToDb();
            String query = "SELECT MAX(id_player) FROM Player;";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            nextID = rs.getInt("max") + 1;
            disconnectDB();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\u001B[33m" + "NEXT ID: " + nextID + "\u001B[0m");
        return nextID;
    }
}

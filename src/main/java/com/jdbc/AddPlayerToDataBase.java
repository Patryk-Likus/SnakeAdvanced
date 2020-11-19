package com.jdbc;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;


public class AddPlayerToDataBase extends DataBaseConst {

    public AddPlayerToDataBase(String name, String login, char[] password) {
        ArrayList<Character> charList = new ArrayList<>();
        for (char ch : password){
            charList.add(ch);
        }
        try {
            connectToDb();
            Array array = conn.createArrayOf("varchar", charList.toArray());

            prepStmt = conn.prepareStatement("INSERT INTO Player (id_player, namePlayer, loginPlayer, passwordPlayer) VALUES (?, ?, ?, ?)");
            prepStmt.setInt(1, returnNextID());
            prepStmt.setString(2, name);
            prepStmt.setString(3, login);
            prepStmt.setArray(4, array);
            prepStmt.executeUpdate();
            disconnectDB();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        showTable("Player");
    }
}

package com.gui;

import com.jdbc.FindPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ScoreBoardMenu extends JPanel {

    RootPanel rootPanel;

    public ScoreBoardMenu(RootPanel gameFrame) {
        this.rootPanel = gameFrame;

        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane(initTable());
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(scrollPane);

        ButtonMenu menuButton = new ButtonMenu("Back to Menu");
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(menuButton, gbc);


        menuButton.addActionListener(e -> {
            System.out.println("Wróc do menu");

                rootPanel.switchPanel(rootPanel.getMainMenu());

        });

    }

    private JTable initTable(){
        JTable table =  new JTable(buildTableModel(new FindPlayer().getScores())) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(400, 400));
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.green);
        table.setForeground(Color.white);
        table.setBackground(Color.BLACK);
        table.setFont(new Font("Arial", Font.BOLD, 28));
        table.setRowHeight(32);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel model = table.getColumnModel();
        model.getColumn(0).setPreferredWidth(6);
//        table.setColumnModel(model);

        return table;
    }


    public static DefaultTableModel buildTableModel(ResultSet rs) {

        ResultSetMetaData metaData;
        Vector<String> columnNames = null;
        Vector<Vector<Object>> data = null;
        try {
            metaData = rs.getMetaData();

            // names of columns
            columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new DefaultTableModel(data, columnNames);

    }

}




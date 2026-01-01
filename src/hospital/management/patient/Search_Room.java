package hospital.management.patient;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;


public class Search_Room extends JPanel {
    Choice choice;
    JTable table;

    public Search_Room() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //  PHẦN TIÊU ĐỀ & BỘ LỌC
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBackground(new Color(90, 156, 163));

        // Tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel title = new JLabel("Search for Room");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);

        //  Bộ lọc (Status)
        JPanel filterPanel = new JPanel();
        filterPanel.setOpaque(false);
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        choice = new Choice();
        choice.add("Availability");
        choice.add("Occupied");

        filterPanel.add(statusLabel);
        filterPanel.add(choice);

        northPanel.add(titlePanel);
        northPanel.add(filterPanel);
        add(northPanel, BorderLayout.NORTH);

        // BẢNG DỮ LIỆU (CENTER)
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);


        loadTableData("select * from Room");

        // PHẦN NÚT BẤM
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(45, 90, 120));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setPreferredSize(new Dimension(100, 30));
        searchBtn.addActionListener((ActionEvent e) -> {
            String query = "select * from Room where Availability = '" + choice.getSelectedItem() + "'";
            loadTableData(query);
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setPreferredSize(new Dimension(100, 30));
        backBtn.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
        });

        footer.add(searchBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void loadTableData(String query) {
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
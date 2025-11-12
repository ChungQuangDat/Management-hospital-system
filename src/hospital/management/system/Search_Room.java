package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Search_Room extends JFrame {
    Choice choice;
    JTable table;

    public Search_Room() {
        setTitle("Search Room");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("Search for Room");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);


        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(90, 156, 163));
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        choice = new Choice();
        choice.add("Availability");
        choice.add("Occupied");
        topPanel.add(statusLabel);
        topPanel.add(choice);
        add(topPanel, BorderLayout.PAGE_START);


        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        loadTableData("select * from Room");


        JPanel footer = new JPanel();
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener((ActionEvent e) -> {
            String query = "select * from Room where Availability = '" + choice.getSelectedItem() + "'";
            loadTableData(query);
        });

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        footer.add(searchBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
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

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(Search_Room::new);
    }
}
package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Room extends JFrame {
    JTable table;

    public Room() {
        setTitle("Room Information");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        try {
            connect c = new connect();
            String q = "SELECT * FROM room";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(90, 156, 163));
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        rightPanel.add(imageLabel);
        add(rightPanel, BorderLayout.EAST);


        JPanel footer = new JPanel();
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener((ActionEvent e) -> setVisible(false));
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Room::new);
    }
}

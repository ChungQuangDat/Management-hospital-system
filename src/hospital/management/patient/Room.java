package hospital.management.patient;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;


public class Room extends JPanel {
    JTable table;

    public Room() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // PHẦN BẢNG DỮ LIỆU (CENTER)
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Tạo lề cho bảng
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
        rightPanel.setPreferredSize(new Dimension(250, 0));
        rightPanel.setLayout(new GridBagLayout());

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        if (imageIcon.getIconWidth() != -1) { // Kiểm tra nếu ảnh tồn tại
            Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            rightPanel.add(imageLabel);
        }
        add(rightPanel, BorderLayout.EAST);

        //  PHẦN NÚT BẤM
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
        });
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }
}
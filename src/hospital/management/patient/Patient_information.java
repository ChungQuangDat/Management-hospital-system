package hospital.management.patient;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;


public class Patient_information extends JPanel {

    public Patient_information() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //  TIÊU ĐỀ
        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("Patient Information");
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  BẢNG DỮ LIỆU
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        // Giữ nguyên AUTO_RESIZE_OFF để các cột giãn theo độ rộng thiết lập thủ công
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       //Thiết lập thanh cuộn (nên sửa lại)
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));

        add(scrollPane, BorderLayout.CENTER);


        try {
            connect c = new connect();
            String q = "SELECT * FROM patient_information";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Thiết lập độ rộng cột chính xác
            int[] colWidths = {200, 150, 150, 80, 200, 60, 250, 100};
            for (int i = 0; i < colWidths.length; i++) {
                if (i < table.getColumnModel().getColumnCount()) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  NÚT BẤM QUAY LẠI
        JPanel footer = new JPanel();
        footer.setBackground(new Color(230, 240, 245));
        JButton backButton = new JButton("BACK");
        backButton.setBackground(new Color(45, 90, 120));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setFocusPainted(false);


        backButton.addActionListener(e -> {
            this.setVisible(false);
        });
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }
}
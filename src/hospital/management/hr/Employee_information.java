package hospital.management.hr;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;


public class Employee_information extends JPanel {

    public Employee_information() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //  TIÊU ĐỀ PHỤ
        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("EMPLOYEE INFORMATION");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // BẢNG DỮ LIỆU (CENTER)
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);


        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM EMP_INFOR");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //  PHẦN NÚT BẤM (SOUTH)
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 30));

        // Khi bấm Back, xóa panel
        backButton.addActionListener(e -> {
            this.setVisible(false);
        });
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }
}
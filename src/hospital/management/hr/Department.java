package hospital.management.hr;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;


public class Department extends JPanel {
    JTable table;

    public Department() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //  PHẦN TIÊU ĐỀ CON
        JPanel header = new JPanel();
        header.setBackground(new Color(90,156,163));
        JLabel title = new JLabel("Department Information");
        title.setFont(new Font("Tahoma", Font.BOLD, 20)); // Chỉnh nhỏ lại một chút cho cân đối
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  PHẦN BẢNG DỮ LIỆU
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        loadTableData();

        //  PHẦN NÚT BẤM
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setPreferredSize(new Dimension(100, 30));


        backBtn.addActionListener(e -> {
            this.setVisible(false);
        });
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void loadTableData() {
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
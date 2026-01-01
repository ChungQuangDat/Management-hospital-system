package hospital.management.hr;

import Auth.connect;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class View_Attendance extends JPanel {
    JTable table;

    public View_Attendance() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //  Header
        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("EMPLOYEE ATTENDANCE LOG");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  Bảng hiển thị
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(90, 156, 163));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Tải dữ liệu từ SQL
        loadData();

        // Footer Buttons
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.setBackground(new Color(45, 90, 120));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.addActionListener(e -> loadData());

        JButton backBtn = new JButton("BACK");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> this.setVisible(false));

        footer.add(refreshBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void loadData() {
        try {
            connect c = new connect();
            // Lấy dữ liệu từ bảng attendance
            String query = "select * from attendance order by date DESC";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
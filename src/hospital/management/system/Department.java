package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Department extends JFrame {
    JTable table;

    public Department() {
        setTitle("Department Information");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel header = new JPanel();
        header.setBackground(new Color(90,156,163));
        JLabel title = new JLabel("Department Information");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);


        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadTableData();


        JPanel footer = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> setVisible(false));
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Department::new);
    }
}

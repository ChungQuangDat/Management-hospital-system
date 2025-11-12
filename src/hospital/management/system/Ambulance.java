package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Ambulance extends JFrame {

    public Ambulance() {
        setTitle("Ambulance Information");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        setupLookAndFeel();

        setLayout(new BorderLayout());


        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        loadTableData(table);

        add(scrollPane, BorderLayout.CENTER);


        JPanel footer = new JPanel();

        JButton backBtn = new JButton("BACK");
        backBtn.addActionListener(e -> setVisible(false));


        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void loadTableData(JTable table) {
        try {
            connect c = new connect();
            String q = "select * from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading Ambulance data: " + e.getMessage());
        }
    }


    private void setupLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus Look and Feel not available. Using default.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ambulance::new);
    }
}
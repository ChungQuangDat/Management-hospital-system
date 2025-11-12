package hospital.management.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Patient_information extends JFrame {

    public Patient_information() {

        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Patient Information");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("Patient Information");
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);


        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));

        add(scrollPane, BorderLayout.CENTER);


        try {
            connect c = new connect();
            String q = "SELECT * FROM patient_information";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));


            int[] colWidths = {200, 150, 150, 80, 200, 60, 250, 100};
            for (int i = 0; i < colWidths.length; i++) {
                if (i < table.getColumnModel().getColumnCount()) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JPanel footer = new JPanel();
        footer.setBackground(new Color(230, 240, 245));
        JButton backButton = new JButton("BACK");
        backButton.setBackground(new Color(45, 90, 120));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> setVisible(false));
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);


        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Patient_information::new);
    }
}

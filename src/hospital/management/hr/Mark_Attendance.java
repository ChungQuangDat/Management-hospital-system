package hospital.management.hr;

import Auth.connect;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Mark_Attendance extends JPanel {
    Choice nameChoice;
    JLabel dateLabel, statusLabel;

    public Mark_Attendance() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        //  Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        JLabel title = new JLabel("EMPLOYEE ATTENDANCE");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //Dùng GridBagLayout để căn giữa màn hình)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;


        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblName = new JLabel("Select Employee:");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        nameChoice = new Choice();
        nameChoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select Name from emp_infor");
            while (rs.next()) {
                nameChoice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        formPanel.add(nameChoice, gbc);


        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblDate = new JLabel("Date:");
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
        formPanel.add(lblDate, gbc);

        gbc.gridx = 1;
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateLabel = new JLabel(currentDate);
        dateLabel.setForeground(Color.YELLOW); // Làm nổi bật ngày
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        formPanel.add(dateLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        //  Footer Buttons
        JPanel footer = new JPanel();
        footer.setOpaque(false);

        JButton submitBtn = new JButton("MARK PRESENT");
        submitBtn.setBackground(new Color(45, 90, 120));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Tahoma", Font.BOLD, 14));

        submitBtn.addActionListener(e -> {
            try {
                connect c = new connect();
                String empName = nameChoice.getSelectedItem();
                String date = dateLabel.getText();


                String query = "insert into attendance values('" + empName + "', '" + date + "', 'Present')";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Attendance marked successfully for " + empName);
            } catch (Exception ex) {
                // Nếu bị trùng khóa chính (đã chấm công rồi)
                JOptionPane.showMessageDialog(null, "Error: Attendance already marked for today!");
            }
        });

        JButton backBtn = new JButton("BACK");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        backBtn.addActionListener(e -> this.setVisible(false));

        footer.add(submitBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }
}
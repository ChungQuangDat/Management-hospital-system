package hospital.management.hr;

import Auth.connect;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Payroll_Management extends JPanel {
    Choice nameChoice;
    JLabel labelSalary, labelDays, labelTotal;

    public Payroll_Management() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        JPanel main = new JPanel(new GridBagLayout());
        main.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // chọn Employee
        JLabel l1 = new JLabel("Employee Name:"); l1.setForeground(Color.WHITE); l1.setFont(new Font("Tahoma", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; main.add(l1, gbc);

        nameChoice = new Choice();
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select Name from emp_infor");
            while(rs.next()) nameChoice.add(rs.getString("Name"));
        } catch(Exception e) { e.printStackTrace(); }
        gbc.gridx = 1; main.add(nameChoice, gbc);

        // Info Labels
        addLabel(main, gbc, "Base Salary:", labelSalary = new JLabel("0"), 1);
        addLabel(main, gbc, "Days Present:", labelDays = new JLabel("0"), 2);
        addLabel(main, gbc, "Net Salary:", labelTotal = new JLabel("0"), 3);
        labelTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelTotal.setForeground(Color.ORANGE);

        JButton calcBtn = new JButton("CALCULATE & VIEW");
        calcBtn.setBackground(new Color(45, 90, 120)); calcBtn.setForeground(Color.WHITE);
        calcBtn.addActionListener(e -> {
            try {
                connect c = new connect();
                // Lấy lương cơ bản
                ResultSet rs1 = c.statement.executeQuery("select Salary from emp_infor where Name = '"+nameChoice.getSelectedItem()+"'");
                int baseSalary = 0;
                if(rs1.next()) baseSalary = Integer.parseInt(rs1.getString("Salary"));

                // Đếm ngày chấm công
                ResultSet rs2 = c.statement.executeQuery("select count(*) as days from attendance where name = '"+nameChoice.getSelectedItem()+"' and status = 'Present'");
                int days = 0;
                if(rs2.next()) days = rs2.getInt("days");

                double total = (baseSalary / 26.0) * days;

                labelSalary.setText(String.valueOf(baseSalary));
                labelDays.setText(String.valueOf(days));
                labelTotal.setText(String.format("%.2f", total));

            } catch(Exception ex) { ex.printStackTrace(); }
        });

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        main.add(calcBtn, gbc);

        add(main, BorderLayout.CENTER);
    }

    private void addLabel(JPanel p, GridBagConstraints gbc, String text, JLabel label, int y) {
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1;
        JLabel l = new JLabel(text); l.setForeground(Color.WHITE); l.setFont(new Font("Tahoma", Font.BOLD, 14));
        p.add(l, gbc);
        gbc.gridx = 1;
        label.setForeground(Color.WHITE); label.setFont(new Font("Tahoma", Font.BOLD, 14));
        p.add(label, gbc);
    }
}
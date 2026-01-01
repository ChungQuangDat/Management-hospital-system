package hospital.management.hr;
import Auth.connect;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Update_Employee_details extends JPanel {
    Choice nameChoice;
    JTextField textSalary, textPhone, textEmail;
    JLabel labelJob;

    public Update_Employee_details() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        JLabel title = new JLabel("UPDATE / DELETE EMPLOYEE");
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;


        gbc.gridx = 0; gbc.gridy = 0;
        JLabel l1 = new JLabel("Select Name:"); l1.setForeground(Color.WHITE); l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(l1, gbc);
        gbc.gridx = 1;
        nameChoice = new Choice();
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select Name from emp_infor");
            while(rs.next()) nameChoice.add(rs.getString("Name"));
        } catch(Exception e) { e.printStackTrace(); }
        formPanel.add(nameChoice, gbc);


        addLabelAndField(formPanel, gbc, "Job:", labelJob = new JLabel("---"), 1);
        addLabelAndField(formPanel, gbc, "Salary:", textSalary = new JTextField(15), 2);
        addLabelAndField(formPanel, gbc, "Phone:", textPhone = new JTextField(15), 3);
        addLabelAndField(formPanel, gbc, "Email:", textEmail = new JTextField(15), 4);

        add(formPanel, BorderLayout.CENTER);


        JPanel footer = new JPanel();
        footer.setOpaque(false);
        JButton checkBtn = new JButton("CHECK");
        JButton updateBtn = new JButton("UPDATE");
        JButton deleteBtn = new JButton("DELETE");

        styleButton(checkBtn); styleButton(updateBtn); styleButton(deleteBtn);

        checkBtn.addActionListener(e -> {
            try {
                connect c = new connect();
                ResultSet rs = c.statement.executeQuery("select * from emp_infor where Name = '"+nameChoice.getSelectedItem()+"'");
                if(rs.next()) {
                    labelJob.setText(rs.getString("Job"));
                    textSalary.setText(rs.getString("Salary"));
                    textPhone.setText(rs.getString("Phone"));
                    textEmail.setText(rs.getString("Email"));
                }
            } catch(Exception ex) { ex.printStackTrace(); }
        });

        updateBtn.addActionListener(e -> {
            try {
                connect c = new connect();
                c.statement.executeUpdate("update emp_infor set Salary='"+textSalary.getText()+"', Phone='"+textPhone.getText()+"', Email='"+textEmail.getText()+"' where Name='"+nameChoice.getSelectedItem()+"'");
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            } catch(Exception ex) { ex.printStackTrace(); }
        });

        deleteBtn.addActionListener(e -> {
            try {
                connect c = new connect();
                c.statement.executeUpdate("delete from emp_infor where Name='"+nameChoice.getSelectedItem()+"'");
                JOptionPane.showMessageDialog(null, "Deleted Successfully");
                this.setVisible(false);
            } catch(Exception ex) { ex.printStackTrace(); }
        });

        footer.add(checkBtn); footer.add(updateBtn); footer.add(deleteBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void addLabelAndField(JPanel p, GridBagConstraints gbc, String label, JComponent field, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        JLabel l = new JLabel(label); l.setForeground(Color.WHITE); l.setFont(new Font("Tahoma", Font.BOLD, 14));
        p.add(l, gbc);
        gbc.gridx = 1;
        if(field instanceof JLabel) ((JLabel) field).setForeground(Color.YELLOW);
        p.add(field, gbc);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(45, 90, 120));
        btn.setForeground(Color.WHITE);
    }
}
package hospital.management.hr;
import Auth.connect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;


public class New_Employee extends JPanel {
    JTextField textName, textAge, textSalary, textPhone, textEmail;
    JComboBox<String> genderBox, jobBox;

    public New_Employee() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        JLabel title = new JLabel("ADD EMPLOYEE DETAILS");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Các trường nhập liệu
        addLabelAndField(formPanel, gbc, "Name:", textName = new JTextField(15), 0);
        addLabelAndField(formPanel, gbc, "Age:", textAge = new JTextField(15), 1);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel l3 = new JLabel("Gender:"); l3.setForeground(Color.WHITE); l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(l3, gbc);
        gbc.gridx = 1;
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        formPanel.add(genderBox, gbc);

        addLabelAndField(formPanel, gbc, "Job:", jobBox = new JComboBox<>(new String[]{"Doctor", "Nurse", "Accountant", "Security", "Janitor"}), 3);
        addLabelAndField(formPanel, gbc, "Salary:", textSalary = new JTextField(15), 4);
        addLabelAndField(formPanel, gbc, "Phone:", textPhone = new JTextField(15), 5);
        addLabelAndField(formPanel, gbc, "Email:", textEmail = new JTextField(15), 6);

        add(formPanel, BorderLayout.CENTER);

        // Footer Buttons
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        JButton saveBtn = new JButton("SAVE");
        saveBtn.setBackground(new Color(45, 90, 120));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.addActionListener(e -> saveEmployee());

        JButton backBtn = new JButton("BACK");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> this.setVisible(false));

        footer.add(saveBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void addLabelAndField(JPanel p, GridBagConstraints gbc, String label, JComponent field, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        JLabel l = new JLabel(label); l.setForeground(Color.WHITE); l.setFont(new Font("Tahoma", Font.BOLD, 14));
        p.add(l, gbc);
        gbc.gridx = 1;
        p.add(field, gbc);
    }

    private void saveEmployee() {
        try {
            connect c = new connect();
            String query = "insert into emp_infor values('"+textName.getText()+"', '"+textAge.getText()+"', '"+genderBox.getSelectedItem()+"', '"+jobBox.getSelectedItem()+"', '"+textSalary.getText()+"', '"+textPhone.getText()+"', '"+textEmail.getText()+"')";
            c.statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Employee Added Successfully");
            this.setVisible(false);
        } catch (Exception e) { e.printStackTrace(); }
    }
}

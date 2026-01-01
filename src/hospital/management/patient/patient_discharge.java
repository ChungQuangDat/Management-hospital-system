package hospital.management.patient;

import Auth.connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Date;


public class patient_discharge extends JPanel {
    Choice choice;
    JLabel RNo, INTime, OUTTime;

    public patient_discharge() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163)); // màu xanh

        //  Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        JLabel title = new JLabel("Check Out");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Phone number
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel label1 = new JLabel("Phone number:");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label1, gbc);

        gbc.gridx = 1;
        choice = new Choice();
        choice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(choice, gbc);

        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from Patient_information");
            while (rs.next()) {
                choice.add(rs.getString("number"));
            }
        } catch (Exception e) { e.printStackTrace(); }

        // Room Number
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel label2 = new JLabel("Room Number:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label2, gbc);

        gbc.gridx = 1;
        RNo = new JLabel("---");
        RNo.setForeground(Color.WHITE);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(RNo, gbc);

        // In Time
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel label3 = new JLabel("In Time:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label3, gbc);

        gbc.gridx = 1;
        INTime = new JLabel("---");
        INTime.setForeground(Color.WHITE);
        INTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(INTime, gbc);

        // Out Time
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel label4 = new JLabel("Out Time:");
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label4, gbc);

        gbc.gridx = 1;
        OUTTime = new JLabel(new Date().toString());
        OUTTime.setForeground(Color.WHITE);
        OUTTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(OUTTime, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Footer Buttons
        JPanel footer = new JPanel();
        footer.setOpaque(false);

        JButton dischargeBtn = new JButton("Discharge");
        dischargeBtn.setBackground(new Color(45, 90, 120));
        dischargeBtn.setForeground(Color.WHITE);
        dischargeBtn.addActionListener((ActionEvent e) -> dischargePatient());

        JButton checkBtn = new JButton("Check");
        checkBtn.setBackground(new Color(45, 90, 120));
        checkBtn.setForeground(Color.WHITE);
        checkBtn.addActionListener((ActionEvent e) -> checkPatient());

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener((ActionEvent e) -> this.setVisible(false));

        footer.add(dischargeBtn);
        footer.add(checkBtn);
        footer.add(backBtn);

        add(footer, BorderLayout.SOUTH);
    }

    private void checkPatient() {
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery(
                    "select * from Patient_information where Number = '" + choice.getSelectedItem() + "'"
            );
            if (rs.next()) {
                RNo.setText(rs.getString("Room_Number"));
                INTime.setText(rs.getString("Time"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void dischargePatient() {
        try {
            connect c = new connect();
            c.statement.executeUpdate("delete from Patient_information where number = '" + choice.getSelectedItem() + "'");
            c.statement.executeUpdate("update Room set Availability = 'Available' where vacant_room = '" + RNo.getText() + "'");
            JOptionPane.showMessageDialog(null, "The patient was successfully discharged!");
            this.setVisible(false);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
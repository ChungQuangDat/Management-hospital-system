package hospital.management.patient;

import Auth.connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;


public class Update_patient_details extends JPanel {
    Choice choice;
    JTextField textFieldR, textFieldINTIME, textFieldAmount, textFieldPending;

    public Update_patient_details() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        //  Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        JLabel title = new JLabel("Update Patient Details");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  Chứa Form và Ảnh
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        //  Form Panel (Bên trái)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel label1 = new JLabel("Name:");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label1, gbc);

        gbc.gridx = 1;
        choice = new Choice();
        choice.setPreferredSize(new Dimension(150, 25));
        formPanel.add(choice, gbc);

        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from Patient_information");
            while (rs.next()){
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) { e.printStackTrace(); }

        // Room Number
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel label2 = new JLabel("Room Number:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label2, gbc);

        gbc.gridx = 1;
        textFieldR = new JTextField(15);
        formPanel.add(textFieldR, gbc);

        // In Time
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel label3 = new JLabel("In Time:");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label3, gbc);

        gbc.gridx = 1;
        textFieldINTIME = new JTextField(15);
        formPanel.add(textFieldINTIME, gbc);

        // Amount Paid
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel label4 = new JLabel("Amount Paid:");
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label4, gbc);

        gbc.gridx = 1;
        textFieldAmount = new JTextField(15);
        formPanel.add(textFieldAmount, gbc);

        // Pending Amount
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel label5 = new JLabel("Pending Amount:");
        label5.setForeground(Color.WHITE);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(label5, gbc);

        gbc.gridx = 1;
        textFieldPending = new JTextField(15);
        textFieldPending.setEditable(false); // Không cho sửa ô nợ
        formPanel.add(textFieldPending, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        //  Image Panel (Bên phải)
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        JPanel imgPanel = new JPanel();
        imgPanel.setOpaque(false);
        imgPanel.add(imageLabel);
        mainPanel.add(imgPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Footer Buttons
        JPanel footer = new JPanel();
        footer.setOpaque(false);

        JButton checkBtn = new JButton("Check");
        checkBtn.setBackground(new Color(45, 90, 120));
        checkBtn.setForeground(Color.WHITE);
        checkBtn.addActionListener((ActionEvent e) -> checkPatient());

        JButton updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(45, 90, 120));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.addActionListener((ActionEvent e) -> updatePatient());

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener((ActionEvent e) -> this.setVisible(false));

        footer.add(updateBtn);
        footer.add(checkBtn);
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void checkPatient() {
        try {
            connect c = new connect();
            String name = choice.getSelectedItem();
            ResultSet rs = c.statement.executeQuery("select * from Patient_information where Name = '"+name+"'");
            if (rs.next()) {
                textFieldR.setText(rs.getString("Room_Number"));
                textFieldINTIME.setText(rs.getString("Time"));
                textFieldAmount.setText(rs.getString("Deposit"));

                ResultSet rs2 = c.statement.executeQuery("select * from Room where vacant_room = '"+textFieldR.getText()+"'");
                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("Price"));
                    int paid = Integer.parseInt(textFieldAmount.getText());
                    textFieldPending.setText(""+(price - paid));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updatePatient() {
        try {
            connect c = new connect();
            String name = choice.getSelectedItem();
            String oldRoom = null;
            ResultSet rsOld = c.statement.executeQuery("SELECT Room_Number FROM Patient_information WHERE Name = '"+name+"'");
            if (rsOld.next()) { oldRoom = rsOld.getString("Room_Number"); }

            String newRoom = textFieldR.getText();
            String time = textFieldINTIME.getText();
            String amount = textFieldAmount.getText();

            c.statement.executeUpdate("UPDATE Patient_information SET Room_Number='"+newRoom+"', Time='"+time+"', Deposit='"+amount+"' WHERE Name='"+name+"'");

            if (oldRoom != null && !oldRoom.equals(newRoom)) {
                c.statement.executeUpdate("UPDATE Room SET Availability='Available' WHERE vacant_room='"+oldRoom+"'");
                c.statement.executeUpdate("UPDATE Room SET Availability='Occupied' WHERE vacant_room='"+newRoom+"'");
            }

            JOptionPane.showMessageDialog(null, "Updated successfully!");
            this.setVisible(false);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
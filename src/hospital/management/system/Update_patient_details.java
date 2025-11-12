package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Update_patient_details extends JFrame {
    Choice choice;
    JTextField textFieldR, textFieldINTIME, textFieldAmount, textFieldPending;

    public Update_patient_details() {
        setTitle("Update Patient Details");
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(90,156,163));
        JLabel title = new JLabel("Update Patient Details");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(90,156,163));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel label1 = new JLabel("Name:");
        label1.setForeground(Color.WHITE);
        formPanel.add(label1, gbc);

        gbc.gridx = 1;
        choice = new Choice();
        formPanel.add(choice, gbc);

        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from Patient_information");
            while (rs.next()){
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) { e.printStackTrace(); }

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel label2 = new JLabel("Room Number:");
        label2.setForeground(Color.WHITE);
        formPanel.add(label2, gbc);

        gbc.gridx = 1;
        textFieldR = new JTextField(15);
        formPanel.add(textFieldR, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel label3 = new JLabel("In Time:");
        label3.setForeground(Color.WHITE);
        formPanel.add(label3, gbc);

        gbc.gridx = 1;
        textFieldINTIME = new JTextField(15);
        formPanel.add(textFieldINTIME, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel label4 = new JLabel("Amount Paid:");
        label4.setForeground(Color.WHITE);
        formPanel.add(label4, gbc);

        gbc.gridx = 1;
        textFieldAmount = new JTextField(15);
        formPanel.add(textFieldAmount, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel label5 = new JLabel("Pending Amount:");
        label5.setForeground(Color.WHITE);
        formPanel.add(label5, gbc);

        gbc.gridx = 1;
        textFieldPending = new JTextField(15);
        formPanel.add(textFieldPending, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        JPanel imgPanel = new JPanel();
        imgPanel.setBackground(new Color(90,156,163));
        imgPanel.add(imageLabel);
        mainPanel.add(imgPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        JButton checkBtn = new JButton("Check");
        checkBtn.addActionListener((ActionEvent e) -> checkPatient());
        JButton updateBtn = new JButton("Update");
        updateBtn.addActionListener((ActionEvent e) -> updatePatient());
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        footer.add(updateBtn);
        footer.add(backBtn);
        footer.add(checkBtn);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
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

            // Lấy phòng cũ
            String oldRoom = null;
            ResultSet rsOld = c.statement.executeQuery("SELECT Room_Number FROM Patient_information WHERE Name = '"+name+"'");
            if (rsOld.next()) {
                oldRoom = rsOld.getString("Room_Number");
            }

            // Dữ liệu mới
            String newRoom = textFieldR.getText();
            String time = textFieldINTIME.getText();
            String amount = textFieldAmount.getText();

            // Kiểm tra xem phòng mới có đang bị occupied không
            ResultSet rsCheck = c.statement.executeQuery("SELECT Availability FROM Room WHERE vacant_room = '"+newRoom+"'");
            if (rsCheck.next()) {
                String status = rsCheck.getString("Availability");
                if (status.equalsIgnoreCase("Occupied") && !newRoom.equals(oldRoom)) {
                    JOptionPane.showMessageDialog(null, "This room is already occupied! Please choose another room.");
                    return;
                }
            }

            // Cập nhật bệnh nhân
            c.statement.executeUpdate("UPDATE Patient_information SET Room_Number='"+newRoom+"', Time='"+time+"', Deposit='"+amount+"' WHERE Name='"+name+"'");

            // Cập nhật trạng thái phòng
            if (oldRoom != null && !oldRoom.equals(newRoom)) {
                c.statement.executeUpdate("UPDATE Room SET Availability='Availability' WHERE vacant_room='"+oldRoom+"'");
                c.statement.executeUpdate("UPDATE Room SET Availability='Occupied' WHERE vacant_room='"+newRoom+"'");
            }

            JOptionPane.showMessageDialog(null, "Updated successfully!");
            setVisible(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Update_patient_details::new);
    }
}

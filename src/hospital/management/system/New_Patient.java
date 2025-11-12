package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class New_Patient extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2;
    Choice c1;
    JLabel dateLabel;
    JButton b1, b2;

    public New_Patient() {
        setTitle("New Patient Form");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel header = new JPanel();
        header.setBackground(new Color(90, 156, 163));
        JLabel title = new JLabel("New Patient Form");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);


        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(230, 240, 245));
        formPanel.setLayout(null);



        JLabel idlable = new JLabel("ID:");
        idlable.setBounds(50,30,180,25);
        idlable.setForeground(new Color(50,50,50));
        idlable.setFont(new Font("Tahoma",Font.BOLD,14));
        formPanel.add(idlable);

        comboBox = new JComboBox<>(new String[]{"Citizen ID", "Passport", "Driving license"});
        comboBox.setBounds(250, 30, 200, 25);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        formPanel.add(comboBox);

        JLabel labelNumber = new JLabel("Phone:");
        labelNumber.setBounds(50, 70, 180, 25);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNumber.setForeground(new Color(50, 50, 50));
        formPanel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(250, 70, 200, 25);
        formPanel.add(textFieldNumber);

        JLabel labelName = new JLabel("Full Name:");
        labelName.setBounds(50, 110, 180, 25);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName.setForeground(new Color(50, 50, 50));
        formPanel.add(labelName);

        textName = new JTextField();
        textName.setBounds(250, 110, 200, 25);
        formPanel.add(textName);

        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(50, 150, 180, 25);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(new Color(50, 50, 50));
        formPanel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(250, 150, 80, 25);
        r1.setBackground(new Color(230, 240, 245));
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(340, 150, 100, 25);
        r2.setBackground(new Color(230, 240, 245));
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(50, 190, 180, 25);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(new Color(50, 50, 50));
        formPanel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(250, 190, 200, 25);
        formPanel.add(textFieldDisease);

        JLabel labelRoom = new JLabel("Room:");
        labelRoom.setBounds(50, 230, 180, 25);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(new Color(50, 50, 50));
        formPanel.add(labelRoom);

        c1 = new Choice();
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from Room");
            while (rs.next()) {
                c1.add(rs.getString("vacant_room"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(250, 230, 200, 25);
        formPanel.add(c1);

        JLabel labelDate = new JLabel("Time:");
        labelDate.setBounds(50, 270, 180, 25);
        labelDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDate.setForeground(new Color(50, 50, 50));
        formPanel.add(labelDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        dateLabel = new JLabel(sdf.format(now));
        dateLabel.setBounds(250, 270, 250, 25);
        dateLabel.setForeground(new Color(50, 50, 50));
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(dateLabel);

        JLabel labelDeposit = new JLabel("Deposit:");
        labelDeposit.setBounds(50, 310, 180, 25);
        labelDeposit.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDeposit.setForeground(new Color(50, 50, 50));
        formPanel.add(labelDeposit);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(250, 310, 200, 25);
        formPanel.add(textFieldDeposite);


        b1 = new JButton("ADD");
        b1.setBounds(180, 370, 120, 35);
        b1.setBackground(new Color(90, 156, 163));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Tahoma", Font.BOLD, 16));
        b1.setFocusPainted(false);
        b1.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 140), 2));
        b1.addActionListener(this);
        formPanel.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(320, 370, 120, 35);
        b2.setBackground(new Color(90, 156, 163));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Tahoma", Font.BOLD, 16));
        b2.setFocusPainted(false);
        b2.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 140), 2));
        b2.addActionListener(this);
        formPanel.add(b2);


        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
        scrollPane.getVerticalScrollBar().setBackground(new Color(230, 240, 245));
        scrollPane.getVerticalScrollBar().setForeground(new Color(90, 156, 163));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 12));
        scrollPane.getHorizontalScrollBar().setBackground(new Color(230, 240, 245));
        scrollPane.getHorizontalScrollBar().setForeground(new Color(90, 156, 163));

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            connect c = new connect();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = gender;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = dateLabel.getText();
            String s8 = textFieldDeposite.getText();

            try {
                String q = "insert into patient_information values('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q1 = "Update Room set Availability = 'Occupied' where vacant_room = '" + s6 + "'";
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(New_Patient::new);
    }
}

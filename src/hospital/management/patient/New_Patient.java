package hospital.management.patient;

import Auth.connect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class New_Patient extends JPanel implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    JRadioButton r1, r2;
    Choice c1;
    JLabel dateLabel;
    JButton b1, b2;

    public New_Patient() {
        setLayout(new BorderLayout());
        setBackground(new Color(90, 156, 163));

        //  PHẦN HEADER
        JPanel header = new JPanel();
        header.setOpaque(false); // Để lộ màu nền của Panel chính
        JLabel title = new JLabel("ADD NEW PATIENT");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        //  PHẦN FORM (Dùng GridBagLayout để căn giữa)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Khoảng cách giữa các ô
        gbc.anchor = GridBagConstraints.WEST;

        // ID Type
        addLabel(formPanel, gbc, "ID Type:", 0);
        comboBox = new JComboBox<>(new String[]{"Citizen ID", "Passport", "Driving license"});
        gbc.gridx = 1; formPanel.add(comboBox, gbc);

        // Phone
        addLabel(formPanel, gbc, "Phone:", 1);
        textFieldNumber = new JTextField(15);
        gbc.gridx = 1; formPanel.add(textFieldNumber, gbc);

        // Full Name
        addLabel(formPanel, gbc, "Full Name:", 2);
        textName = new JTextField(15);
        gbc.gridx = 1; formPanel.add(textName, gbc);

        // Gender (Radio Buttons)
        addLabel(formPanel, gbc, "Gender:", 3);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.setOpaque(false);
        r1 = new JRadioButton("Male");
        r1.setForeground(Color.WHITE); r1.setOpaque(false);
        r2 = new JRadioButton("Female");
        r2.setForeground(Color.WHITE); r2.setOpaque(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2);
        genderPanel.add(r1); genderPanel.add(r2);
        gbc.gridx = 1; formPanel.add(genderPanel, gbc);

        // Disease
        addLabel(formPanel, gbc, "Disease:", 4);
        textFieldDisease = new JTextField(15);
        gbc.gridx = 1; formPanel.add(textFieldDisease, gbc);

        // Room
        addLabel(formPanel, gbc, "Room:", 5);
        c1 = new Choice();
        try {
            connect c = new connect();
            ResultSet rs = c.statement.executeQuery("select * from Room");
            while (rs.next()) {
                c1.add(rs.getString("room_no")); // Đảm bảo đúng tên cột trong DB
            }
        } catch (Exception e) { e.printStackTrace(); }
        gbc.gridx = 1; formPanel.add(c1, gbc);

        // Time
        addLabel(formPanel, gbc, "Time:", 6);
        dateLabel = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dateLabel.setForeground(Color.YELLOW);
        dateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 1; formPanel.add(dateLabel, gbc);

        // Deposit
        addLabel(formPanel, gbc, "Deposit:", 7);
        textFieldDeposite = new JTextField(15);
        gbc.gridx = 1; formPanel.add(textFieldDeposite, gbc);

        add(formPanel, BorderLayout.CENTER);

        //  PHẦN NÚT BẤM
        JPanel footer = new JPanel();
        footer.setOpaque(false);

        b1 = new JButton("ADD");
        styleButton(b1, new Color(45, 90, 120));
        b1.addActionListener(this);

        b2 = new JButton("BACK");
        styleButton(b2, Color.BLACK);
        b2.addActionListener(this);

        footer.add(b1);
        footer.add(b2);
        add(footer, BorderLayout.SOUTH);
    }

    // Hàm tạo Label chữ trắng
    private void addLabel(JPanel p, GridBagConstraints gbc, String text, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        p.add(lbl, gbc);
    }

    // Hàm tạo Style cho nút
    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            JOptionPane.showMessageDialog(null, "Added Successfully");
        } else if (e.getSource() == b2) {
            this.setVisible(false);
        }
    }
}
package hospital.management.dashboard;

import Auth.Login;
import hospital.management.hr.*;
import hospital.management.patient.*;
import hospital.management.patient.Room;
import hospital.management.patient.Search_Room;
import javax.swing.*;
import java.awt.*;

public class Reception extends JFrame {
    private JPanel content;

    public Reception() {
        setTitle("Bach Mai Hospital Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 850);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER (Tiêu đề & Logo)
        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));
        header.setPreferredSize(new Dimension(0, 150));
        header.setLayout(null);

        ImageIcon leftIcon = new ImageIcon(ClassLoader.getSystemResource("icon/OIP.jpg"));
        JLabel leftLabel = new JLabel(new ImageIcon(leftIcon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH)));
        leftLabel.setBounds(15, 5, 200, 140);
        header.add(leftLabel);

        JLabel title = new JLabel(" BACH MAI HOSPITAL MANAGEMENT SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(350, 45, 800, 60);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // SIDEBAR TRÁI (BỆNH NHÂN & PHÒNG)
        JPanel leftSidebar = new JPanel();
        leftSidebar.setBackground(new Color(230, 240, 245));
        leftSidebar.setLayout(new GridLayout(7, 1, 5, 10));
        leftSidebar.setPreferredSize(new Dimension(250, 0));
        leftSidebar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Patient & Room Management"));

        String[] patientBtns = {
                "Add New Patient", "Patient List", "Discharge Patient",
                "Update Patient", "Room Status", "Search Room", "Department"
        };
        String[] patientIcons = {
                "icon/748137.png", "icon/medical-information.png", "icon/6902395.png",
                "icon/updated.png", "icon/15192686.png", "icon/954591.png", "icon/17774557.png"
        };

        for (int i = 0; i < patientBtns.length; i++) {
            JButton btn = createMenuButton(patientBtns[i], patientIcons[i]);
            final int index = i;
            btn.addActionListener(e -> handleButtonClick(index));
            leftSidebar.add(btn);
        }
        add(leftSidebar, BorderLayout.WEST);

        //  SIDEBAR PHẢI (NHÂN SỰ,LƯƠNG,ACCOUNT)
        JPanel rightSidebar = new JPanel();
        rightSidebar.setBackground(new Color(230, 240, 245));
        rightSidebar.setLayout(new GridLayout(7, 1, 5, 10));
        rightSidebar.setPreferredSize(new Dimension(250, 0));
        rightSidebar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HR & Payroll System"));

        String[] hrBtns = {
                "Employee Info", "Add Employee", "Update Employee",
                "Mark Attendance", "View Attendance", "Payroll", "Logout"
        };
        String[] hrIcons = {
                "icon/14076511.png", "icon/748137.png", "icon/updated.png",
                "icon/img.png", "icon/img_1.png", "icon/img_2.png", "icon/4400629.png"
        };

        for (int i = 0; i < hrBtns.length; i++) {
            JButton btn = createMenuButton(hrBtns[i], hrIcons[i]);
            final int index = i + 7;
            btn.addActionListener(e -> handleButtonClick(index));
            rightSidebar.add(btn);
        }
        add(rightSidebar, BorderLayout.EAST);

        // TRUNG TÂM (KHUNG HIỂN THỊ)
        content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());

        // Màn hình chờ mặc định
        JLabel welcome = new JLabel("Wishing you a pleasant day", JLabel.CENTER);
        welcome.setFont(new Font("Tahoma", Font.ITALIC, 22));
        welcome.setForeground(Color.LIGHT_GRAY);
        content.add(welcome, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createMenuButton(String name, String iconPath) {
        JButton btn = new JButton(name);
        btn.setBackground(new Color(45, 90, 120));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(12);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            java.net.URL url = ClassLoader.getSystemResource(iconPath);
            if (url != null) {
                btn.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
            }
        } catch(Exception e) {}
        return btn;
    }

    private void handleButtonClick(int index) {
        content.removeAll();
        switch (index) {
            // Nhóm Trái (0-6)
            case 0: content.add(new New_Patient(), BorderLayout.CENTER); break;
            case 1: content.add(new Patient_information(), BorderLayout.CENTER); break;
            case 2: content.add(new patient_discharge(), BorderLayout.CENTER); break;
            case 3: content.add(new Update_patient_details(), BorderLayout.CENTER); break;
            case 4: content.add(new Room(), BorderLayout.CENTER); break;
            case 5: content.add(new Search_Room(), BorderLayout.CENTER); break;
            case 6: content.add(new Department(), BorderLayout.CENTER); break;

            // Nhóm Phải (7-13)
            case 7: content.add(new Employee_information(), BorderLayout.CENTER); break;
            case 8: content.add(new New_Employee(), BorderLayout.CENTER); break;
            case 9: content.add(new Update_Employee_details(), BorderLayout.CENTER); break;
            case 10: content.add(new Mark_Attendance(), BorderLayout.CENTER); break;
            case 11: content.add(new View_Attendance(), BorderLayout.CENTER); break;
            case 12: content.add(new Payroll_Management(), BorderLayout.CENTER); break;
            case 13:
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    new Login();
                }
                return;
        }
        content.revalidate();
        content.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Reception::new);
    }
}
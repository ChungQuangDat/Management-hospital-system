package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame {

    public Reception() {
        setTitle("Hospital Management System - Reception");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(109, 164, 170));
        header.setPreferredSize(new Dimension(0, 150)); // Header cao 150px
        header.setLayout(null);

        //Điều chỉnh Ảnh góc trái (OIP.jpg) to hơn nữa
        ImageIcon leftIcon = new ImageIcon(ClassLoader.getSystemResource("icon/OIP.jpg"));
        // Tăng kích thước lên đáng kể, ví dụ: 200x140 (cho chiều cao phù hợp với header 150px)
        // Hoặc 180x140 nếu muốn chiều rộng ảnh không quá lớn.
        // Dùng 200x140 để nó to rõ rệt hơn.
        int leftImageWidth = 200; // Chiều rộng mới của ảnh trái
        int leftImageHeight = 140; // Chiều cao mới của ảnh trái
        Image leftImg = leftIcon.getImage().getScaledInstance(leftImageWidth, leftImageHeight, Image.SCALE_SMOOTH);
        JLabel leftLabel = new JLabel(new ImageIcon(leftImg));
        // Điều chỉnh vị trí để nó vẫn cách lề trên 15 và cách lề trái 10, với kích thước mới
        leftLabel.setBounds(10, (150 - leftImageHeight) / 2, leftImageWidth, leftImageHeight); // Căn giữa theo chiều dọc trong header
        header.add(leftLabel);

        //Điều chỉnh Tiêu đề để căn giữa hoàn hảo hơn sau khi ảnh trái to hơn
        int headerTotalWidth = 1200; // Chiều rộng của JFrame
        int rightImageWidth = 120; // Chiều rộng của ảnh bác sĩ bên phải
        int rightImageMarginRight = 50; // Khoảng cách từ ảnh bác sĩ đến lề phải của header

        // Tính toán vị trí x và chiều rộng cho tiêu đề:
        // Tiêu đề sẽ bắt đầu sau ảnh trái (leftLabel) một khoảng trống.
        // leftLabel.getX() + leftLabel.getWidth() + margin_between_left_image_and_title
        int titleStartX = leftLabel.getX() + leftLabel.getWidth() + 30; // 30px là khoảng trống

        // Tiêu đề sẽ kết thúc trước ảnh phải (logoLabel) một khoảng trống.
        // logoLabel.getX() - margin_between_title_and_right_image
        int titleEndX = (headerTotalWidth - rightImageMarginRight - rightImageWidth) - 30; // 30px là khoảng trống

        int titleWidth = titleEndX - titleStartX;

        // Đảm bảo titleWidth không âm hoặc quá nhỏ
        if (titleWidth < 100) { // Nếu quá nhỏ, đặt một giá trị mặc định an toàn
            titleWidth = 600;
            titleStartX = (headerTotalWidth - titleWidth) / 2;
        }

        JLabel title = new JLabel("Hospital Management System", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBounds(titleStartX, 30, titleWidth, 50); // Cập nhật vị trí và kích thước
        header.add(title);

        //Điều chỉnh Ảnh bác sĩ góc phải (dr.png) xích sang phải một chút
        ImageIcon rightIcon = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image logoImg = rightIcon.getImage().getScaledInstance(rightImageWidth, 120, Image.SCALE_SMOOTH); // Sử dụng rightImageWidth
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        // Tính toán vị trí x cho ảnh bác sĩ để nó cách lề phải một khoảng nhất định
        int logoLabelX = headerTotalWidth - rightImageWidth - rightImageMarginRight; // 1200 - 120 - 50 = 1030
        logoLabel.setBounds(logoLabelX, 15, rightImageWidth, 120); // Cập nhật vị trí x
        header.add(logoLabel);

        add(header, BorderLayout.NORTH);




        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(230, 240, 245));
        sidebar.setLayout(new GridLayout(10, 1, 5, 5));
        sidebar.setPreferredSize(new Dimension(220, 0));


        JButton[] buttons = new JButton[10];
        String[] btnNames = {
                "Add new patient", "Room", "Department",
                "All employee info", "Patient information", "Patient Discharge",
                "Update patient details", "Hospital ambulance", "Search room", "Logout"
        };


        String[] iconPaths = {
                "icon/748137.png", "icon/15192686.png", "icon/17774557.png",
                "icon/14076511.png", "icon/medical-information.png", "icon/6902395.png",
                "icon/updated.png", "icon/2894975.png", "icon/954591.png", "icon/4400629.png"
        };

        for (int i = 0; i < 10; i++) {
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setBackground(new Color(45, 90, 120));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setFont(new Font("Tahoma", Font.BOLD, 14));
            buttons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttons[i].setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


            java.net.URL iconUrl = ClassLoader.getSystemResource(iconPaths[i]);
            if (iconUrl != null) {
                ImageIcon icon = new ImageIcon(new ImageIcon(iconUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                buttons[i].setIcon(icon);
            }

            sidebar.add(buttons[i]);
        }

        add(sidebar, BorderLayout.WEST);


        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        add(content, BorderLayout.CENTER);


        buttons[0].addActionListener(e -> new New_Patient());
        buttons[1].addActionListener(e -> new Room());
        buttons[2].addActionListener(e -> new Department());
        buttons[3].addActionListener(e -> new Employee_information());
        buttons[4].addActionListener(e -> new Patient_information());
        buttons[5].addActionListener(e -> new patient_discharge());
        buttons[6].addActionListener(e -> new Update_patient_details());
        buttons[7].addActionListener(e -> new Ambulance());
        buttons[8].addActionListener(e -> new Search_Room());
        buttons[9].addActionListener(e -> {
            setVisible(false);
            new Login();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Reception::new);
    }
}

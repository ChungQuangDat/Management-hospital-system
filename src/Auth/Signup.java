package Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Signup extends JFrame implements ActionListener {

    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signupButton, backButton;

    public Signup() {
        setTitle("Đăng Ký Hệ Thống");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 450, 450, 40, 40));


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(70, 50, 140), // Đảo ngược màu một chút để phân biệt
                        getWidth(), getHeight(), new Color(0, 180, 200)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Create Account", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        backgroundPanel.add(title, gbc);
        gbc.gridwidth = 1;

        // Username
        addLabel("Username", 1, backgroundPanel, gbc);
        usernameField = createTextField();
        gbc.gridx = 1; backgroundPanel.add(usernameField, gbc);

        // Email
        addLabel("Email", 2, backgroundPanel, gbc);
        emailField = createTextField();
        gbc.gridx = 1; backgroundPanel.add(emailField, gbc);

        // Password
        addLabel("Password", 3, backgroundPanel, gbc);
        passwordField = createPasswordField();
        gbc.gridx = 1; backgroundPanel.add(passwordField, gbc);

        // Confirm Password
        addLabel("Confirm Pass", 4, backgroundPanel, gbc);
        confirmPasswordField = createPasswordField();
        gbc.gridx = 1; backgroundPanel.add(confirmPasswordField, gbc);

        // Buttons
        signupButton = new JButton("Register");
        backButton = new JButton("Back");

        styleButton(signupButton, new Color(46, 204, 113)); // Màu xanh lá
        styleButton(backButton, new Color(149, 165, 166));   // Màu xám

        gbc.gridx = 0; gbc.gridy = 5;
        backgroundPanel.add(signupButton, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(backButton, gbc);

        add(backgroundPanel);
        setVisible(true);
    }


    private void addLabel(String text, int row, JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(label, gbc);
    }


    private JTextField createTextField() {
        JTextField tf = new JTextField(15);
        tf.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        tf.setBackground(new Color(245, 245, 245));
        return tf;
    }


    private JPasswordField createPasswordField() {
        JPasswordField pf = new JPasswordField(15);
        pf.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        pf.setBackground(new Color(245, 245, 245));
        return pf;
    }


    private void styleButton(JButton b, Color bgColor) {
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 14));
        b.setPreferredSize(new Dimension(120, 40));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(bgColor);
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String user = usernameField.getText();
            String email = emailField.getText();
            String pass = new String(passwordField.getPassword());
            String confirmPass = new String(confirmPasswordField.getPassword());


            if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all the required information.!");
                return;
            }


            if (!pass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "The verification password does not match.!");
                return;
            }

            try {

                connect c = new connect();



                String query = "INSERT INTO login (ID, password, email) VALUES ('"
                        + user + "', '" + pass + "', '" + email + "')";


                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Account registration successful!");


                new Login();
                setVisible(false);

            } catch (Exception ex) {
                // Nếu trùng ID (Username) sẽ báo lỗi vì ID là Primary Key
                if (ex.getMessage().contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "The username already exists.!");
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu!");
                }
            }
        } else if (e.getSource() == backButton) {
            new Login();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}

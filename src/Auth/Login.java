package Auth;

import hospital.management.dashboard.Reception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);//Căn cửa sổ vào giữa màn
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 450, 350, 40, 40));

        // Panel nền gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(0, 180, 200),
                        getWidth(), getHeight(), new Color(70, 50, 140)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(title, gbc);
        gbc.gridwidth = 1;

        JLabel userLabel = new JLabel("Username/email");
        userLabel.setForeground(Color.LIGHT_GRAY);
        userLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        usernameField.setBackground(new Color(245, 245, 245));
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 1;
        backgroundPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.LIGHT_GRAY);
        passLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        passwordField.setBackground(new Color(245, 245, 245));
        gbc.gridx = 1;
        backgroundPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        // Style nút
        for (JButton b : new JButton[]{loginButton, cancelButton}) {
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setFont(new Font("Tahoma", Font.BOLD, 14));
            b.setPreferredSize(new Dimension(100, 40));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.addActionListener(this);
        }

        loginButton.setBackground(new Color(0, 150, 255));
        loginButton.setForeground(Color.WHITE);

        cancelButton.setBackground(new Color(255, 80, 80));
        cancelButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(cancelButton, gbc);

        add(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                connect c = new connect();
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                String q = "SELECT * FROM login WHERE (ID = '" + user + "' OR email = '" + user + "') AND password = '" + pass + "'";
                ResultSet rs = c.statement.executeQuery(q);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Welcome " + user + "!");
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }
}

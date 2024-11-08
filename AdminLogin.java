package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {

    public AdminLogin() {
        // Frame setup
        setTitle("Admin Login");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
//        JLabel signUpLabel = new JLabel("Sign Up");
//        signUpLabel.setForeground(Color.BLUE);
//        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        add(signUpLabel, gbc);

        // Event handling
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passField.getPassword());

                // Placeholder logic for login validation
                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }
            }
        });

//        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                dispose(); // Close admin login
                
//*******************************************************************************************************
//                AdminSignUp adminSignUp = new AdminSignUp(); // Open admin sign-up form
//                adminSignUp.setVisible(true);
//*******************************************************************************************************
//            }
//        });
    }

    public static void main(String[] args) {
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setVisible(true);
    }
}

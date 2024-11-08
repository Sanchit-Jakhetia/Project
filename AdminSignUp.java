package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSignUp extends JFrame {

    public AdminSignUp() {
        // Frame setup
        setTitle("Admin Sign Up");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        // Components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JLabel confirmPassLabel = new JLabel("Confirm :");
        JPasswordField confirmPassField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmPassLabel, gbc);

        gbc.gridx = 1;
        add(confirmPassField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(loginLabel, gbc);

        // Event handling
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                }
            }
        });

        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); // Close sign up form
                AdminLogin adminLogin = new AdminLogin(); // Open admin login form
                adminLogin.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        AdminSignUp adminSignUp = new AdminSignUp();
        
        adminSignUp.setVisible(true);
    }
}

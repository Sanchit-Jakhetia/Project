package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {

    public LoginForm() {
        // Set up the frame
        setTitle("Login Form");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Create components
        JLabel phoneLabel = new JLabel("Mobile Number:");
        JTextField phoneField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // GridBagLayout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(signUpLabel, gbc);

        // Event Handling
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String password = new String(passField.getPassword());

                // Validate phone number
                if (!ValidationUtils.isValidPhoneNumber(phone)) {
                    String phoneErrorMessage = "Invalid phone number. Please ensure:\n" +
                                               "- It contains exactly 10 digits.\n" +
                                               "- It starts with a digit between 7 and 9.";
                    JOptionPane.showMessageDialog(null, phoneErrorMessage);
                } else {
                    // Logic for login verification goes here
                    // Placeholder for successful login message or failure message
                    JOptionPane.showMessageDialog(null, "Phone: " + phone + "\nPassword: " + password);
                }
            }
        });



        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Logic to go to Registration Form
                dispose();  // Close login form
                RegistrationForm registrationForm = new RegistrationForm(); // Open registration form
                registrationForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}

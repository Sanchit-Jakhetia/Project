package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {

    public RegistrationForm() {
        // Set up the frame
        setTitle("Registration Form");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Create components
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Mobile Number:");
        JTextField phoneField = new JTextField(15);

        JLabel courseLabel = new JLabel("Select Course:");
        String[] courses = {"Select Course", "B.Tech", "B.Pharma", "M.Tech"};
        JComboBox<String> courseComboBox = new JComboBox<>(courses);

        JLabel specializationLabel = new JLabel("Select Specialization:");
        String[] specializations = {"Select Specialization", "CSE", "IT", "CS","ECE"};
        JComboBox<String> specializationComboBox = new JComboBox<>(specializations);

        JCheckBox agreeCheckBox = new JCheckBox("I agree to receive notifications");

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPassField = new JPasswordField(15);

        JButton registerButton = new JButton("Register");
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // GridBagLayout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(firstNameLabel, gbc);

        gbc.gridx = 1;
        add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lastNameLabel, gbc);

        gbc.gridx = 1;
        add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(courseLabel, gbc);

        gbc.gridx = 1;
        add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(specializationLabel, gbc);

        gbc.gridx = 1;
        add(specializationComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(agreeCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(confirmPassLabel, gbc);

        gbc.gridx = 1;
        add(confirmPassField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(loginLabel, gbc);

        // Event Handling
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                String password = new String(passField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                // Logic for registration verification goes here
                if (!ValidationUtils.isValidPassword(password)) {
                    String passwordErrorMessage = "Invalid password. It must meet the following criteria:\n" +
                                                   "- At least 8 characters long\n" +
                                                   "- At least one uppercase letter\n" +
                                                   "- At least one lowercase letter\n" +
                                                   "- At least one digit\n" +
                                                   "- At least one special character (e.g., @, #, $, %, ^, &, +, =, !)";
                    JOptionPane.showMessageDialog(null, passwordErrorMessage);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                }
            }
        });



        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Logic to go back to Login Form
                dispose();  // Close registration form
                LoginForm loginForm = new LoginForm(); // Open login form
                loginForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
    }
}

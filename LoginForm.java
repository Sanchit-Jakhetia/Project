package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoginForm extends JFrame {

    public LoginForm() {
        // Set up the frame
        setTitle("Login Form");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

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
                                               "- It starts with a digit between 6 and 9.";
                    JOptionPane.showMessageDialog(null, phoneErrorMessage);
                } else {
                    // Check credentials from file
                    if (verifyLogin(phone, password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        // Open the StudentDashboard after successful login
                        dispose();  // Close the login form
                        new StudentDashboard(phone);  // Open the student dashboard with the phone number of the logged-in user
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                    }
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

    private boolean verifyLogin(String phone, String password) {
        // File path for StudentDetails.txt
        String studentFilePath = "D:\\Codes\\Java\\University\\src\\maintenance\\StudentDetails.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(","); // Assuming format: firstName,lastName,phone,email,course,specialization,password
                if (credentials.length == 7) {
                    // Check if the phone number and password match
                    if (credentials[2].equals(phone) && credentials[6].equals(password)) {
                        return true; // Login successful
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
        return false; // If no matching credentials are found
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}

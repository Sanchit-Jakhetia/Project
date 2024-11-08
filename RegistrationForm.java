package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm extends JFrame {

    // File path variable to easily change the location of the file
    private static final String studentFilePath = "D:\\Codes\\Java\\University\\src\\maintenance\\StudentDetails.txt"; // Change this path if needed
    private static final String emailFilePath = "D:\\Codes\\Java\\University\\src\\maintenance\\Email.txt"; // File to store emails for notifications

    // Declare form components as instance variables
    private JTextField firstNameField, lastNameField, phoneField, emailField;
    private JPasswordField passField, confirmPassField;
    private JComboBox<String> courseComboBox, specializationComboBox;
    private JCheckBox agreeCheckBox;

    public RegistrationForm() {
        // Set up the frame
        setTitle("Registration Form");
        setSize(400, 550);  // Increased size to accommodate email field
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create components
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Mobile Number:");
        phoneField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15); // Email input field

        JLabel courseLabel = new JLabel("Select Course:");
        String[] courses = {"Select Course", "B.Tech", "B.Pharma", "M.Tech"};
        courseComboBox = new JComboBox<>(courses);

        JLabel specializationLabel = new JLabel("Select Specialization:");
        String[] specializations = {"Select Specialization", "CSE", "IT", "CS", "ECE"};
        specializationComboBox = new JComboBox<>(specializations);

        agreeCheckBox = new JCheckBox("I agree to receive notifications");

        JLabel passLabel = new JLabel("Password:");
        passField = new JPasswordField(15);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassField = new JPasswordField(15);

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
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(emailField, gbc);  // Added email field

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(courseLabel, gbc);

        gbc.gridx = 1;
        add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(specializationLabel, gbc);

        gbc.gridx = 1;
        add(specializationComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(agreeCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(confirmPassLabel, gbc);

        gbc.gridx = 1;
        add(confirmPassField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        add(loginLabel, gbc);

        // Event Handling
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String course = (String) courseComboBox.getSelectedItem();
                String specialization = (String) specializationComboBox.getSelectedItem();
                String password = new String(passField.getPassword());
                String confirmPassword = new String(confirmPassField.getPassword());

                // Logic for email validation
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
                    return; // Stop further processing if email is invalid
                }

                // Logic for registration verification
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
                    // Registration Successful - Save to CSV file
                    try {
                        FileWriter fileWriter = new FileWriter(studentFilePath, true); // Open file in append mode
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        // Prepare student details as a comma-separated string
                        String studentData = firstName + "," + lastName + "," + phone + "," + email + "," + course + "," + specialization + "," + password;

                        // Write student data to the file
                        bufferedWriter.write(studentData);
                        bufferedWriter.newLine(); // Move to the next line for the next entry

                        // Close the file writer
                        bufferedWriter.close();

                        // If user agrees to receive notifications, save the email to Email.txt
                        if (agreeCheckBox.isSelected()) {
                            saveEmailForNotifications(email);
                        }

                        JOptionPane.showMessageDialog(null, "Registration Successful and data saved!");

                        // Clear the form after successful registration
                        clearForm();

                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error while saving details: " + ioException.getMessage());
                    }
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

    // Email validation method using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to save email to Email.txt if the user opts for notifications
    private void saveEmailForNotifications(String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(emailFilePath, true))) {
            writer.write(email);  // Write email to Email.txt
            writer.newLine(); // Add a new line for the next email
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while saving email for notifications: " + e.getMessage());
        }
    }

    // Method to clear the form
    private void clearForm() {
        // Clear text fields
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        passField.setText("");
        confirmPassField.setText("");

        // Reset combo boxes
        courseComboBox.setSelectedIndex(0);
        specializationComboBox.setSelectedIndex(0);

        // Uncheck checkbox
        agreeCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
    }
}

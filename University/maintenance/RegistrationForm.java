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

    private static final String studentFilePath = "Data\\AdmissionDetails.csv";
    private static final String emailFilePath = "Data\\Email.csv";

    private JTextField firstNameField, lastNameField, phoneField, emailField;
    private JPasswordField passField, confirmPassField;
    private JComboBox<String> courseComboBox, specializationComboBox;
    private JCheckBox agreeCheckBox;

    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "Images\\College.jpg";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255, 200));
        formPanel.setLayout(new GridBagLayout());

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Mobile Number:");
        phoneField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

        JLabel courseLabel = new JLabel("Select Course:");
        String[] courses = {"Select Course", "B.Tech", "M.Tech", "B.Tech(Lateral)"};
        courseComboBox = new JComboBox<>(courses);

        JLabel specializationLabel = new JLabel("Select Specialization:");
        String[] specializations = {"Specialization", "CSE", "CSE(AI)", "ECE(VLSI)", "ME", "IT", "CS", "ECE"};
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(courseLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(courseComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(specializationLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(specializationComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(agreeCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(confirmPassLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPassField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        formPanel.add(loginLabel, gbc);

        backgroundPanel.add(formPanel);

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

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
                    return;
                }

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
                    try {
                        FileWriter fileWriter = new FileWriter(studentFilePath, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        String studentData = firstName + "," + lastName + "," + phone + "," + email + "," + course + "," + specialization + "," + password;
                        bufferedWriter.write(studentData);
                        bufferedWriter.newLine();
                        bufferedWriter.close();

                        if (agreeCheckBox.isSelected()) {
                            saveEmailForNotifications(email);
                        }

                        JOptionPane.showMessageDialog(null, "Registration Successful and data saved!");
                        clearForm();
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error while saving details: " + ioException.getMessage());
                    }
                }
            }
        });

        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void saveEmailForNotifications(String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(emailFilePath, true))) {
            writer.write(email);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while saving email for notifications: " + e.getMessage());
        }
    }

    private void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        passField.setText("");
        confirmPassField.setText("");
        courseComboBox.setSelectedIndex(0);
        specializationComboBox.setSelectedIndex(0);
        agreeCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
    }
}

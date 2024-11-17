package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class LoginForm extends JFrame {
    
    private static final String studentFilePath = "D:\\Data\\AdmissionDetails.csv";
    
    private JTextField mobileField;
    private JPasswordField passwordField;
    
    public LoginForm() {
        setTitle("Login Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mobileLabel, gbc);
        gbc.gridx = 1;
        add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mobile = mobileField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(mobile, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    // Proceed to the student details window
                    displayStudentDetails(mobile);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mobile number or password. Please try again.");
                }
            }
        });

        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                // Open registration form
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            }
        });
    }

    private boolean validateLogin(String mobile, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7) {
                    String registeredMobile = details[2];
                    String registeredPassword = details[6];
                    if (registeredMobile.equals(mobile) && registeredPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
        return false;
    }

    private void displayStudentDetails(String mobile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7 && details[2].equals(mobile)) {
                    // Create a new window to display the student's details
                    JFrame detailsFrame = new JFrame("Student Details");
                    detailsFrame.setSize(300, 300);
                    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    detailsFrame.setLocationRelativeTo(this);

                    // Panel for displaying student details
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

                    // Center the content
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    gbc.anchor = GridBagConstraints.CENTER;

                    // Status color logic
                    JLabel statusLabel = new JLabel("Status: " + details[7]);
                    statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

                    // Change color based on status
                    switch (details[7].toLowerCase()) {
                        case "approved":
                        	statusLabel.setForeground(new Color(0, 128, 0)); // Green for approved
                            break;
                        case "disapproved":
                            statusLabel.setForeground(Color.RED);    // Red for disapproved
                            break;
                        case "pending":
                            statusLabel.setForeground(Color.BLUE);   // Blue for pending
                            break;
                    }

                    panel.add(statusLabel, gbc);

                    // Name
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    panel.add(new JLabel("Name: " + details[0] + " " + details[1]), gbc);

                    // Mobile No
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    panel.add(new JLabel("Mobile No: " + details[2]), gbc);

                    // Email
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    panel.add(new JLabel("Email: " + details[3]), gbc);

                    // Branch
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    panel.add(new JLabel("Branch: " + details[4]), gbc);

                    // Specialization
                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    panel.add(new JLabel("Specialization: " + details[5]), gbc);

                    // Adding the panel to the frame
                    detailsFrame.add(panel);
                    detailsFrame.setVisible(true);
                    break; // Once the matching mobile number is found, display the details and exit the loop
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading student details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}

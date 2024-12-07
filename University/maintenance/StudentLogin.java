package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class StudentLogin extends JFrame {

    public StudentLogin() {
        setTitle("Student Login");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "Images\\College.jpg"; 
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new GridBagLayout()); 
        setContentPane(backgroundPanel);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255, 200));
        formPanel.setLayout(new GridBagLayout());
		
        JLabel phoneLabel = new JLabel("Mobile Number:");
        JTextField phoneField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(loginButton, gbc);

		backgroundPanel.add(formPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String password = new String(passField.getPassword());

                if (!ValidationUtils.isValidPhoneNumber(phone)) {
                    String phoneErrorMessage = "Invalid phone number. Please ensure:\n" +
                                               "- It contains exactly 10 digits.\n" +
                                               "- It starts with a digit between 6 and 9.";
                    JOptionPane.showMessageDialog(null, phoneErrorMessage);
                } else {
                    if (verifyLogin(phone, password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        dispose();
                        openStudentDashboard(phone);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                    }
                }
            }
        });
    }

    private boolean verifyLogin(String phone, String password) {
        String studentFilePath = "Data\\StudentDetails.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 7) {
                    if (credentials[2].equals(phone) && credentials[6].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
        return false;
    }

    private void openStudentDashboard(String phone) {
        StudentDashboard studentDashboard = new StudentDashboard(phone);
        studentDashboard.setVisible(true);
    }

    public static void main(String[] args) {
        StudentLogin loginForm = new StudentLogin();
        loginForm.setVisible(true);
    }
}

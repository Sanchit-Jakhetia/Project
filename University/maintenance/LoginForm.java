package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginForm extends JFrame {

    private static final String studentFilePath = "Data\\AdmissionDetails.csv";
    private JTextField mobileField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login Form");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "Images\\College.jpg";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255, 200));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        loginPanel.add(mobileLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(registerLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mobile = mobileField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(mobile, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    displayStudentDetails(mobile);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid mobile number or password. Please try again.");
                }
            }
        });

        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            }
        });

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        backgroundPanel.add(loginPanel, mainGbc);
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
                    JFrame detailsFrame = new JFrame("Student Details");
                    detailsFrame.setSize(300, 300);
                    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    detailsFrame.setLocationRelativeTo(this);

                    JPanel panel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);

                    JLabel statusLabel = new JLabel("Status: " + details[7]);
                    statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    switch (details[7].toLowerCase()) {
                        case "approved":
                            statusLabel.setForeground(new Color(0, 128, 0));
                            break;
                        case "disapproved":
                            statusLabel.setForeground(Color.RED);
                            break;
                        case "pending":
                            statusLabel.setForeground(Color.BLUE);
                            break;
                    }

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    gbc.anchor = GridBagConstraints.CENTER;
                    panel.add(statusLabel, gbc);

                    gbc.gridwidth = 1;
                    gbc.anchor = GridBagConstraints.WEST;
                    gbc.gridy = 1;
                    panel.add(new JLabel("Name: " + details[0] + " " + details[1]), gbc);

                    gbc.gridy = 2;
                    panel.add(new JLabel("Mobile No: " + details[2]), gbc);

                    gbc.gridy = 3;
                    panel.add(new JLabel("Email: " + details[3]), gbc);

                    gbc.gridy = 4;
                    panel.add(new JLabel("Branch: " + details[4]), gbc);

                    gbc.gridy = 5;
                    panel.add(new JLabel("Specialization: " + details[5]), gbc);

                    detailsFrame.add(panel);
                    detailsFrame.setVisible(true);
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading student details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}
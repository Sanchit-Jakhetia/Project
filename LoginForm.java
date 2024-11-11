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
    
    private static final String studentFilePath = "D:\\Codes\\Java\\University\\src\\maintenance\\AdmissionDetails.csv";
    
    private JTextField emailField;
    private JPasswordField passwordField;
    
    public LoginForm() {
        setTitle("Login Form");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

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
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

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
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    // Proceed to next window or dashboard
//*********************************************************************************************************
                    
                    
                    
//*********************************************************************************************************
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password. Please try again.");
                }
            }
        });

        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
//*********************************************************************************************************
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
//*********************************************************************************************************                
            }
        });
    }

    private boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7) {
                    String registeredEmail = details[3];
                    String registeredPassword = details[6];
                    if (registeredEmail.equals(email) && registeredPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}

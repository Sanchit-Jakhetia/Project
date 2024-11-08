package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        // Frame setup
        setTitle("Welcome to the Project");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridBagLayout());
        
        // Welcome label
        JLabel welcomeLabel = new JLabel("Select an Option:");
        
        // Buttons for User Login and Admin Login
        JButton userLoginButton = new JButton("User Login");
        JButton adminLoginButton = new JButton("Admin Login");
        
        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(welcomeLabel, gbc);

        gbc.gridy = 1;
        add(userLoginButton, gbc);

        gbc.gridy = 2;
        add(adminLoginButton, gbc);
        
        // Event handling for User Login button
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close Start window
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });

        // Event handling for Admin Login button
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close Start window
                // Assuming AdminLogin.java will be created with a similar structure to LoginForm
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        Main startFrame = new Main();
        // Center the frame on the screen
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }
}

package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class AdminLogin extends JFrame {
    
    private static final String adminFilePath = "D:\\Data\\AdminDetails.csv";
    
    private JTextField adminIDField;
    private JPasswordField adminPasswordField;
    
    public AdminLogin() {
        setTitle("Admin Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JLabel adminIDLabel = new JLabel("Admin ID:");
        adminIDField = new JTextField(15);

        JLabel adminPasswordLabel = new JLabel("Password:");
        adminPasswordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(adminIDLabel, gbc);
        gbc.gridx = 1;
        add(adminIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(adminPasswordLabel, gbc);
        gbc.gridx = 1;
        add(adminPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = adminIDField.getText();
                String password = new String(adminPasswordField.getPassword());

                if (validateAdminLogin(ID, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
//*********************************************************************************************************                    
                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setVisible(true);
//*********************************************************************************************************                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid admin ID or password. Please try again.");
                }
            }
        });
    }

    private boolean validateAdminLogin(String ID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(adminFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 2) {
                    String registeredID = details[0];  // Assuming ID is the first field
                    String registeredPassword = details[1]; // Assuming password is the second field
                    if (registeredID.equals(ID) && registeredPassword.equals(password)) {
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
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setVisible(true);
    }
}

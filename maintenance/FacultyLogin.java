package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class FacultyLogin extends JFrame {

    public FacultyLogin() {
        setTitle("Faculty Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JLabel usernameLabel = new JLabel("Phone Number:");
        JTextField usernameField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passField.getPassword());

                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
//*********************************************************************************************************                    
                    FacultyDashboard dashboard = new FacultyDashboard();
                    dashboard.setVisible(true);
//*********************************************************************************************************                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\FacultyDetails.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[4].trim().equals(username) && details[7].trim().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading admin details.");
        }
        return false;
    }

    public static void main(String[] args) {
        FacultyLogin adminLogin = new FacultyLogin();
        adminLogin.setVisible(true);
    }
}

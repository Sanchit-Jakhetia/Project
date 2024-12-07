package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversityLogin extends JFrame {

    public UniversityLogin() {
        setTitle("University Login");
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

        JButton adminLoginButton = new JButton("Admin Login");
        JButton facultyLoginButton = new JButton("Faculty Login");
        JButton studentLoginButton = new JButton("Student Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        formPanel.add(adminLoginButton, gbc);

        gbc.gridy = 1;
        formPanel.add(facultyLoginButton, gbc);

        gbc.gridy = 2;
        formPanel.add(studentLoginButton, gbc);

        backgroundPanel.add(formPanel);

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.setVisible(true);
            }
        });

        facultyLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FacultyLogin facultyLogin = new FacultyLogin();
                facultyLogin.setVisible(true);
            }
        });

        studentLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentLogin studentLogin = new StudentLogin();
                studentLogin.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityLogin::new);
    }
}

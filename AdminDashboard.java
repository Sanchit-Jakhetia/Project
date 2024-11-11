package maintenance;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class AdminDashboard extends JFrame {
    
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageFacultyButton = new JButton("Manage Faculty");
        JButton logoutButton = new JButton("Logout");

        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.NORTH);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(manageStudentsButton);
        panel.add(manageFacultyButton);
        panel.add(logoutButton);
        add(panel, BorderLayout.CENTER);

        logoutButton.addActionListener(e -> {
            dispose();
            UniversityLogin loginForm = new UniversityLogin();
            loginForm.setVisible(true);
        });
    }

    public static void main(String[] args) {
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
    }
}

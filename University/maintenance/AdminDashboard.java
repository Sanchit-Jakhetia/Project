package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "Images\\College.jpg";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 255, 255, 200));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageFacultyButton = new JButton("Manage Faculty");
        JButton viewAdmissionButton = new JButton("Manage Admissions");
        JButton logoutButton = new JButton("Logout");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(manageStudentsButton, gbc);

        gbc.gridy = 1;
        centerPanel.add(manageFacultyButton, gbc);

        gbc.gridy = 2;
        centerPanel.add(viewAdmissionButton, gbc);

        gbc.gridy = 4;
        centerPanel.add(logoutButton, gbc);

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageStudent manageStudent = new ManageStudent();
                manageStudent.setVisible(true);
            }
        });

        manageFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageFaculty manageFaculty = new ManageFaculty();
                manageFaculty.setVisible(true);
            }
        });

        viewAdmissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageAdmission admissionViewer = new ManageAdmission();
                admissionViewer.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDashboard.this, "You have logged out.");
                dispose();
                UniversityLogin loginForm = new UniversityLogin();
                loginForm.setVisible(true);
            }
        });

        GridBagConstraints backgroundGbc = new GridBagConstraints();
        backgroundGbc.gridx = 0;
        backgroundGbc.gridy = 0;
        backgroundGbc.fill = GridBagConstraints.BOTH;
        backgroundGbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(centerPanel, backgroundGbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}
package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") // Just to remove the yellow error line from code
public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

//        JLabel welcomeLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageFacultyButton = new JButton("Manage Faculty");
        JButton viewAdmissionButton = new JButton("Manage Admissions");

        centerPanel.add(manageStudentsButton);
        centerPanel.add(manageFacultyButton);
        centerPanel.add(viewAdmissionButton);

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the ManageStudent.java file
                ManageStudent manageStudent = new ManageStudent();
                manageStudent.setVisible(true);
            }
        });

        manageFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the ManageFaculty.java file
                ManageFaculty manageFaculty = new ManageFaculty();
                manageFaculty.setVisible(true);
            }
        });

        viewAdmissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a Admission viewer
            	ManageAdmission AdmissionViewer = new ManageAdmission();
                AdmissionViewer.setVisible(true);
            }
        });

        //add(welcomeLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDashboard.this, "You have logged out.");
                dispose();
                UniversityLogin loginForm = new UniversityLogin();
                loginForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
    }
}

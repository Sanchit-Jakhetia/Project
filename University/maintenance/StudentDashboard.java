package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDashboard extends JFrame {

    private String loggedInStudentName;

    public StudentDashboard(String phone) {
        this.loggedInStudentName = phone;

        setTitle("Student Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "Images\\College.jpg";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        backgroundPanel.setLayout(new GridBagLayout()); 
        setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout()); 
        centerPanel.setBackground(new Color(255, 255, 255, 200)); 

        JButton viewStudentInfoButton = new JButton("View Information");
        JButton viewTimetableButton = new JButton("View Timetable");
        JButton submitFeedbackButton = new JButton("Submit Feedback");
		JButton logoutButton = new JButton("Logout");

        gbc.gridx = 0; 
        gbc.gridy = 0;
        centerPanel.add(viewStudentInfoButton, gbc);

        gbc.gridy = 1;
        centerPanel.add(viewTimetableButton, gbc);

        gbc.gridy = 2;
        centerPanel.add(submitFeedbackButton, gbc);

		gbc.gridy = 3;
        centerPanel.add(logoutButton, gbc);
		
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        backgroundPanel.add(centerPanel, gbc);

        viewStudentInfoButton.addActionListener(e -> showStudentInfo());
        viewTimetableButton.addActionListener(e -> showTimetable());
        submitFeedbackButton.addActionListener(e -> submitFeedback());
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(StudentDashboard.this, "You have logged out.");
            dispose();
            try {
                UniversityLogin startFrame = new UniversityLogin();
                startFrame.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void showStudentInfo() {
        List<String[]> studentData = readStudentData();
        String[] studentInfo = null;

        for (String[] student : studentData) {
            if (student[2].equals(loggedInStudentName)) {
                studentInfo = student;
                break;
            }
        }

        if (studentInfo != null) {
            String message = "Student Information:\n";
            message += "First Name: " + studentInfo[0] + "\n";
            message += "Last Name: " + studentInfo[1] + "\n";
            message += "Phone: " + studentInfo[2] + "\n";
            message += "Email: " + studentInfo[3] + "\n";
            message += "Course: " + studentInfo[4] + "\n";
            message += "Specialization: " + studentInfo[5] + "\n";

            JOptionPane.showMessageDialog(this, message);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found!");
        }
    }

    private void showTimetable() {
        StringBuilder timetableData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\Timetable.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                timetableData.append(line).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading timetable data.");
        }

        if (timetableData.length() > 0) {
            JOptionPane.showMessageDialog(this, "Your Timetable:\n" + timetableData.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No timetable available.");
        }
    }

    private void submitFeedback() {
        String feedback = JOptionPane.showInputDialog(this, "Enter your feedback:");

        if (feedback != null && !feedback.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data\\Feedback.txt", true))) {
                writer.write("- " + feedback);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Feedback submitted successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error submitting feedback.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Feedback cannot be empty.");
        }
    }

    private List<String[]> readStudentData() {
        List<String[]> studentData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\StudentDetails.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7) {
                    studentData.add(details);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading student data.");
        }
        return studentData;
    }

    public static void main(String[] args) {
        String loggedInStudentName = "John";
        StudentDashboard dashboard = new StudentDashboard(loggedInStudentName);
        dashboard.setVisible(true);
    }
}


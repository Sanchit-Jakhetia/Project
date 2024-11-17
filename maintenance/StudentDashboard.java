package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class StudentDashboard extends JFrame {

    private String loggedInStudentName;

    public StudentDashboard(String phone) {
        this.loggedInStudentName = phone;

        setTitle("Student Dashboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton viewStudentInfoButton = new JButton("View Student Information");
        JButton viewTimetableButton = new JButton("View Timetable");
        JButton submitFeedbackButton = new JButton("Submit Feedback");
        //JButton viewGradesButton = new JButton("View Grades");

        centerPanel.add(viewStudentInfoButton);
        centerPanel.add(viewTimetableButton);
        centerPanel.add(submitFeedbackButton);
        //centerPanel.add(viewGradesButton);

        add(centerPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        add(logoutButton, BorderLayout.SOUTH);

        viewStudentInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStudentInfo();
            }
        });

        viewTimetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimetable();
            }
        });

        submitFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitFeedback();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(StudentDashboard.this, "You have logged out.");
                dispose();
                try {
                	UniversityLogin startFrame = new UniversityLogin();
                    startFrame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\Timetable.txt"))) {
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Data\\Feedback.txt", true))) {
                writer.write("- "+feedback);
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
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\StudentDetails.csv"))) {
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

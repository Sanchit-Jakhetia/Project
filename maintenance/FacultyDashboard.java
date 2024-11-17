package maintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")    // Just to remove the yellow error line from code
public class FacultyDashboard extends JFrame {

    public FacultyDashboard() {
        setTitle("Faculty Dashboard");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Adjusted grid size to accommodate the new search button
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JButton manageStudentsButton = new JButton("View Students");
        JButton modifyTimetableButton = new JButton("View Timetable");
        JButton viewFeedbackButton = new JButton("View Feedback");

        centerPanel.add(manageStudentsButton);
        centerPanel.add(modifyTimetableButton);
        centerPanel.add(viewFeedbackButton);

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageStudents();
            }
        });

        modifyTimetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTimetable();
            }
        });

        viewFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFeedback();
            }
        });

        add(centerPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 40));
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FacultyDashboard.this, "You have logged out.");
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

    private void manageStudents() {
        List<String[]> studentData = readStudentData();

        String[] columns = {"First Name", "Last Name", "Phone", "Email", "Course", "Specialization"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (String[] student : studentData) {
            model.addRow(student);
        }

        JTable studentTable = new JTable(model);
        studentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        JFrame tableFrame = new JFrame("Student Details");
        tableFrame.setSize(600, 400);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableFrame.setLocationRelativeTo(this);

        // Add a search button to the student table window
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Number Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        tableFrame.add(searchPanel, BorderLayout.NORTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mobileNumber = searchField.getText().trim();
                if (!mobileNumber.isEmpty()) {
                    List<String[]> filteredData = searchStudentByMobileNumber(mobileNumber);
                    DefaultTableModel filteredModel = new DefaultTableModel(columns, 0);
                    for (String[] student : filteredData) {
                        filteredModel.addRow(student);
                    }
                    studentTable.setModel(filteredModel);
                }
            }
        });

        tableFrame.add(scrollPane);
        tableFrame.setVisible(true);
    }

    private List<String[]> readStudentData() {
        List<String[]> studentData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\StudentDetails.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7) {
                    String[] studentWithoutPassword = {details[0], details[1], details[2], details[3], details[4], details[5]};
                    studentData.add(studentWithoutPassword);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading student data.");
        }
        return studentData;
    }

    private List<String[]> searchStudentByMobileNumber(String mobileNumber) {
        List<String[]> filteredData = new ArrayList<>();
        List<String[]> studentData = readStudentData();
        for (String[] student : studentData) {
            if (student[2].equals(mobileNumber)) {
                filteredData.add(student);
            }
        }
        return filteredData;
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

    private void showFeedback() {
        StringBuilder feedbackData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\Feedback.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                feedbackData.append(line).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading feedback data.");
        }

        if (feedbackData.length() > 0) {
            JOptionPane.showMessageDialog(this, "Feedback:\n" + feedbackData.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No feedback available.");
        }
    }

    public static void main(String[] args) {
        FacultyDashboard dashboard = new FacultyDashboard();
        dashboard.setVisible(true);
    }
}

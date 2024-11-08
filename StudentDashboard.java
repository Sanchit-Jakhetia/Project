package maintenance;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentDashboard extends JFrame {

    public StudentDashboard(String phone) {
        // Set up the frame
        setTitle("Student Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));  // 7 rows, 2 columns for labels and values

        // Create components
        JLabel firstNameLabel = new JLabel("First Name: ");
        JLabel firstNameValue = new JLabel("");
        
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel lastNameValue = new JLabel("");
        
        JLabel phoneLabel = new JLabel("Mobile Number: ");
        JLabel phoneValue = new JLabel("");
        
        JLabel emailLabel = new JLabel("Email: ");
        JLabel emailValue = new JLabel("");
        
        JLabel courseLabel = new JLabel("Course: ");
        JLabel courseValue = new JLabel("");
        
        JLabel specializationLabel = new JLabel("Specialization: ");
        JLabel specializationValue = new JLabel("");
        
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel passwordValue = new JLabel("******");  // Hide the password
        
        // Add labels and values to the frame
        add(firstNameLabel);
        add(firstNameValue);
        
        add(lastNameLabel);
        add(lastNameValue);
        
        add(phoneLabel);
        add(phoneValue);
        
        add(emailLabel);
        add(emailValue);
        
        add(courseLabel);
        add(courseValue);
        
        add(specializationLabel);
        add(specializationValue);
        
        add(passwordLabel);
        add(passwordValue);

        // Load data from the StudentDetails file
        loadStudentData(phone, firstNameValue, lastNameValue, phoneValue, emailValue, courseValue, specializationValue, passwordValue);

        // Set frame visibility
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadStudentData(String phone, JLabel firstNameValue, JLabel lastNameValue, JLabel phoneValue, JLabel emailValue, JLabel courseValue, JLabel specializationValue, JLabel passwordValue) {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Codes\\Java\\University\\src\\maintenance\\StudentDetails.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                if (studentData.length == 7 && studentData[2].equals(phone)) {
                    // Set the student details into the labels
                    firstNameValue.setText(studentData[0]);
                    lastNameValue.setText(studentData[1]);
                    phoneValue.setText(studentData[2]);
                    emailValue.setText(studentData[3]);
                    courseValue.setText(studentData[4]);
                    specializationValue.setText(studentData[5]);
                    passwordValue.setText("******");  // Hide the password
                    break; // Only show the matching student's data
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading student data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // To test the StudentDashboard, pass a phone number as a parameter (e.g., for the logged-in student)
        new StudentDashboard("9234567890");  // Replace this with a valid phone number
    }
}

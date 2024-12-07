package maintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class ManageStudent extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ManageStudent() {
        setTitle("Manage Students");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"First Name", "Last Name", "Phone", "Email", "Course", "Specialization"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        studentTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadStudentData();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentData();
                JOptionPane.showMessageDialog(ManageStudent.this, "Dashboard refreshed successfully.");
            }
        });
    }

    private void loadStudentData() {
        tableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\StudentDetails.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 6) {
                    String[] studentRecord = {details[0], details[1], details[2], details[3], details[4], details[5]};
                    tableModel.addRow(studentRecord);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading student data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addStudent() {
        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);
        JTextField phoneField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField courseField = new JTextField(15);
        JTextField specializationField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        panel.add(courseField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Specialization:"), gbc);
        gbc.gridx = 1;
        panel.add(specializationField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JDialog dialog = new JDialog(this, "Add New Student", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);

        addButton.addActionListener(e -> {
            String[] studentDetails = {
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                phoneField.getText().trim(),
                emailField.getText().trim(),
                courseField.getText().trim(),
                specializationField.getText().trim(),
                new String(passwordField.getPassword()).trim()
            };

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data\\StudentDetails.csv", true))) {
                writer.write(String.join(",", studentDetails));
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                loadStudentData();
                dialog.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error adding student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void removeStudent() {
        JTextField phoneField = new JTextField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        JDialog dialog = new JDialog(this, "Remove Student", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeButton = new JButton("Remove");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);

        removeButton.addActionListener(e -> {
            String phoneNumber = phoneField.getText().trim();
            if (phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid phone number.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean removed = false;
            File inputFile = new File("Data\\StudentDetails.csv");
            File tempFile = new File("Data\\TempStudentDetails.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (!details[2].equals(phoneNumber)) {
                        writer.write(line);
                        writer.newLine();
                    } else {
                        removed = true;
                    }
                }

                if (removed) {
                    JOptionPane.showMessageDialog(this, "Student removed successfully.");
                    loadStudentData();
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given phone number.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error removing student.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (removed) {
                if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating the student record file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                tempFile.delete();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void searchStudent() {
        JTextField nameField = new JTextField(15);
        JTextField phoneField = new JTextField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Enter Full Name (First Last):"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Enter Phone Number:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        JDialog dialog = new JDialog(this, "Search Student", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("Search");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);

        searchButton.addActionListener(e -> {
            String fullName = nameField.getText().trim();
            String phoneNumber = phoneField.getText().trim();
            boolean studentFound = false;

            File inputFile = new File("Data\\StudentDetails.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                StringBuilder searchResults = new StringBuilder("Matching Students:\n");

                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    String studentFullName = details[0] + " " + details[1];

                    if (studentFullName.equalsIgnoreCase(fullName) || details[2].equals(phoneNumber)) {
                        searchResults.append(String.join(", ", Arrays.copyOf(details, details.length - 1))).append("\n");
                        studentFound = true;
                    }
                }

                if (studentFound) {
                    JOptionPane.showMessageDialog(this, searchResults.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given details.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error searching for student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        ManageStudent manageStudent = new ManageStudent();
        manageStudent.setVisible(true);
    }
}

package maintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

@SuppressWarnings("serial")
public class ManageFaculty extends JFrame {

    private JTable facultyTable;
    private DefaultTableModel tableModel;

    public ManageFaculty() {
        setTitle("Manage Faculty");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"First Name", "Last Name", "Position", "Email", "Phone Number", "Office Location", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        facultyTable = new JTable(tableModel);
        facultyTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(facultyTable);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Faculty");
        JButton removeButton = new JButton("Remove Faculty");
        JButton searchButton = new JButton("Search Faculty");
        JButton refreshButton = new JButton("Refresh"); 

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(refreshButton); 

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadFacultyData();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFaculty();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFaculty();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFaculty();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFacultyData();
                JOptionPane.showMessageDialog(ManageFaculty.this, "Dashboard refreshed successfully.");
            }
        });
    }

    private void loadFacultyData() {
        tableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\FacultyDetails.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 7) {
                    String[] facultyRecord = {details[0], details[1], details[2], details[3], details[4], details[5], details[6]};
                    tableModel.addRow(facultyRecord);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading faculty data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addFaculty() {
        // Create input fields
        JTextField firstNameField = new JTextField(15);
        JTextField lastNameField = new JTextField(15);
        JTextField positionField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField phoneField = new JTextField(15);
        JTextField officeField = new JTextField(15);
        JTextField statusField = new JTextField(15);
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
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Office Location:"), gbc);
        gbc.gridx = 1;
        panel.add(officeField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(statusField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JDialog dialog = new JDialog(this, "Add New Faculty", true);
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
            String[] facultyDetails = {
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                positionField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                officeField.getText().trim(),
                statusField.getText().trim(),
                new String(passwordField.getPassword())
            };

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data\\FacultyDetails.csv", true))) {
                writer.write(String.join(",", facultyDetails));
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Faculty added successfully.");
                loadFacultyData();
                dialog.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error adding faculty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void removeFaculty() {
        JTextField firstNameField = new JTextField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        JDialog dialog = new JDialog(this, "Remove Faculty", true);
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
            String firstName = firstNameField.getText().trim();
            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid value for First Name.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean removed = false;
            File inputFile = new File("Data\\FacultyDetails.csv");
            File tempFile = new File("Data\\tempFacultyDetails.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (!details[0].equalsIgnoreCase(firstName)) {
                        writer.write(line);
                        writer.newLine();
                    } else {
                        removed = true;
                    }
                }
                if (removed) {
                    JOptionPane.showMessageDialog(this, "Faculty removed successfully.");
                    inputFile.delete();
                    tempFile.renameTo(inputFile); 
                    loadFacultyData(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Faculty not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error removing faculty.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); 
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void searchFaculty() {
        JTextField searchField = new JTextField(15);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Search by First Name:"), gbc);
        gbc.gridx = 1;
        panel.add(searchField, gbc);

        JDialog dialog = new JDialog(this, "Search Faculty", true);
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
            String searchQuery = searchField.getText().trim();
            if (searchQuery.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name to search.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean found = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String firstName = (String) tableModel.getValueAt(i, 0);
                if (firstName.equalsIgnoreCase(searchQuery)) {
                    JOptionPane.showMessageDialog(this, "Faculty found: " + firstName);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Faculty not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            dialog.dispose(); 
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageFaculty().setVisible(true));
    }
}

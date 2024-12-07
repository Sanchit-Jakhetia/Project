package maintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ManageAdmission extends JFrame {

    private JTable admissionTable;
    private DefaultTableModel tableModel;

    private static final String CSV_FILE_NAME = "Data\\AdmissionDetails.csv";

    public ManageAdmission() {
        setTitle("Manage Admissions");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Name", "Surname", "Mobile", "Email", "Course", "Specialization", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        admissionTable = new JTable(tableModel);
        admissionTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(admissionTable);

        JPanel buttonPanel = new JPanel();
        JButton approveButton = new JButton("Approve");
        JButton disapproveButton = new JButton("Disapprove");
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(approveButton);
        buttonPanel.add(disapproveButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadAdmissionData();

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveAdmission();
            }
        });

        disapproveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disapproveAdmission();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAdmission();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdmissionData();
                JOptionPane.showMessageDialog(ManageAdmission.this, "Dashboard refreshed successfully.");
            }
        });
    }

    private void loadAdmissionData() {
        tableModel.setRowCount(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 8) {
                
                    String[] admissionRecord = {details[0], details[1], details[2], details[3], details[4], details[5], details[7]};
                    tableModel.addRow(admissionRecord);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading admission data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void approveAdmission() {
        int selectedRow = admissionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No row selected to approve.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setValueAt("Approved", selectedRow, 6);
        updateCSVFile();
        JOptionPane.showMessageDialog(this, "Admission approved successfully.");
    }


    private void disapproveAdmission() {
        int selectedRow = admissionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No row selected to disapprove.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.setValueAt("Disapproved", selectedRow, 6); 
        updateCSVFile();
        JOptionPane.showMessageDialog(this, "Admission disapproved successfully.");
    }


    private void searchAdmission() {
        String keyword = JOptionPane.showInputDialog(this, "Enter keyword to search (name, email, etc.):");
        if (keyword == null || keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No keyword entered for search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                String cellValue = tableModel.getValueAt(i, j).toString().toLowerCase();
                if (cellValue.contains(keyword.toLowerCase())) {
                    admissionTable.setRowSelectionInterval(i, i);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No matching records found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void updateCSVFile() {
        ArrayList<String[]> updatedData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                
                if (row < tableModel.getRowCount()) {
                    details[0] = (String) tableModel.getValueAt(row, 0); 
                    details[1] = (String) tableModel.getValueAt(row, 1);
                    details[2] = (String) tableModel.getValueAt(row, 2); 
                    details[3] = (String) tableModel.getValueAt(row, 3); 
                    details[4] = (String) tableModel.getValueAt(row, 4); 
                    details[5] = (String) tableModel.getValueAt(row, 5); 
                    details[7] = (String) tableModel.getValueAt(row, 6); 
                }

                updatedData.add(details);
                row++;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_NAME))) {
                for (String[] record : updatedData) {
                    writer.write(String.join(",", record));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating admission data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManageAdmission().setVisible(true);
        });
    }
}

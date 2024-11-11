package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class UniversityLogin extends JFrame {

    public UniversityLogin() {
        setTitle("University Login");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JButton adminLoginButton = new JButton("Admin Login");
        JButton facultyLoginButton = new JButton("Faculty Login");
        JButton studentLoginButton = new JButton("Student Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        add(adminLoginButton, gbc);

        gbc.gridy = 1;
        add(facultyLoginButton, gbc);

        gbc.gridy = 2;
        add(studentLoginButton, gbc);

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//*********************************************************************************************************                
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.setVisible(true);
//*********************************************************************************************************                
            }
        });

        facultyLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//*********************************************************************************************************                
                FacultyLogin facultyLogin = new FacultyLogin();
                facultyLogin.setVisible(true);
//*********************************************************************************************************                
            }
        });

        studentLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//*********************************************************************************************************
                StudentLogin studentLogin = new StudentLogin();
                studentLogin.setVisible(true);
//*********************************************************************************************************            
            }
        });
    }

    public static void main(String[] args) {
        UniversityLogin loginFrame = new UniversityLogin();
        loginFrame.setVisible(true);
    }
}

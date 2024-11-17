package maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")    //Just to remove the yellow error line from code
public class Main extends JFrame {

    public Main() {
        setTitle("University Admission & Maintenance System");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        JButton applyAdmissionButton = new JButton("Apply Admission");
        JButton universityLoginButton = new JButton("University Login");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(applyAdmissionButton, gbc);

        gbc.gridy = 1;
        add(universityLoginButton, gbc);

        applyAdmissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//*********************************************************************************************************
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
//*********************************************************************************************************                
            }
        });

        universityLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//*********************************************************************************************************                
                UniversityLogin loginWindow = new UniversityLogin();
                loginWindow.setVisible(true);
//********************************************************************************************************* 
            }
        });
    }

    public static void main(String[] args) {
        Main mainFrame = new Main();
        mainFrame.setVisible(true);
    }
}

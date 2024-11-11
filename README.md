# University Admission & Maintenance System

## Project Overview

The **University Admission & Maintenance System** is a Java-based desktop application designed to streamline and manage university student data and administrative tasks. The system allows both students and administrators to interact through a user-friendly interface built using **Java Swing**. It includes features such as student registration, login, feedback, and timetable management, as well as administrative functionalities for managing student details, course information, and notifications.

This system supports a **student portal** for managing personal information, course selection, and feedback submission, and an **admin dashboard** for managing student records, viewing feedback, and maintaining university operations. 

## Features

### Student Portal:
- **Student Registration**: Allows new students to register by providing personal details, selecting courses, and setting a password for future login.
- **Student Login**: Students can securely log into the system using their registered email and password.
- **Timetable View**: Students can view their personalized timetable after login, which can only be modified by administrators.
- **Feedback System**: Students can submit feedback and ratings regarding the courses they are enrolled in.
- **Attendance Tracking**: Students can view their attendance details, which are updated by the admin.

### Admin Dashboard:
- **Admin Login**: The admin can securely log in to the system using credentials stored in a CSV file.
- **Student Management**: Admins can view and manage student records, including adding new students and updating existing information.
- **Course and Specialization Management**: Admins can manage available courses and their specializations, ensuring up-to-date data for students to choose from.
- **Timetable Management**: Admins can modify the timetable, ensuring the schedule reflects the most current academic offerings.
- **Notification System**: Admins can send notifications to students about important updates, announcements, or changes in the system.

## Technologies Used

- **Java Swing**: For the graphical user interface (GUI), providing a desktop-based application for both the admin and student interfaces.
- **File Handling**: For data storage and management of student and admin records using CSV files.
- **Regular Expressions**: For validating student and admin login credentials, including email and password formats.
- **BufferedWriter**: For saving student registration details and admin login credentials into CSV files.

## How to Run

### Prerequisites:
1. **Java Development Kit (JDK)**: Ensure that Java is installed on your machine.
2. **IDE**: You can use any IDE for Java development, such as IntelliJ IDEA, Eclipse, or NetBeans.
   
### Steps to Run the Project:
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/UniversityAdmissionMaintenanceSystem.git
   ```
   
2. **Open the Project**:
   Open the project in your preferred IDE.
   
3. **Compile and Run**:
   - Compile the Java files.
   - Run the `Main` class to start the system.
   
   Alternatively, you can run individual components like the **RegistrationForm**, **LoginForm**, **AdminLogin**, and **AdminDashboard** from their respective files.

## File Structure

```
UniversityAdmissionMaintenanceSystem/
│
├── src/
│   ├── maintenance/
│   │   ├── AdminDashboard.java
│   │   ├── AdminLogin.java
│   │   ├── LoginForm.java
│   │   ├── RegistrationForm.java
│   │   ├── StudentDetails.csv
│   │   ├── AdminDetails.csv
│   │   └── Email.csv
│   └── Main.java
└── README.md
```

- `AdminDashboard.java`: The admin dashboard UI where the administrator manages student data, timetables, and other operations.
- `AdminLogin.java`: The admin login window for authentication.
- `LoginForm.java`: The student login window for authentication.
- `RegistrationForm.java`: The student registration form for new users.
- `StudentDetails.csv`: A CSV file containing student records with their details.
- `AdminDetails.csv`: A CSV file containing admin credentials for login validation.
- `Email.csv`: A CSV file to store student emails for sending notifications.
  
## CSV Files

### StudentDetails.csv
This file stores the details of registered students:
```
FirstName,LastName,MobileNumber,Email,Course,Specialization,Password
```

### AdminDetails.csv
This file stores the credentials of admin users:
```
AdminEmail,AdminPassword
```

### Email.csv
This file stores the emails of students who have agreed to receive notifications.

## Future Enhancements
- **Database Integration**: Replace file-based storage with a relational database for better scalability and security.
- **Advanced User Authentication**: Implement password hashing and secure login methods for better security.
- **Admin Management**: Allow the admin to modify the student's timetable directly through the interface.
- **Student Dashboard**: Add features such as viewing grades, exam results, and course progress.

## Conclusion

The **University Admission & Maintenance System** offers a comprehensive solution for managing university operations and student information. This project provides a simple yet powerful approach to handling registrations, timetables, and feedback while offering easy-to-use interfaces for both students and administrators. The modular structure of the project allows for easy future enhancements and scalability.

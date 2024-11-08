# Project
# University Admission & Maintenance System

A Java-based project designed to streamline the university admission process by offering features such as student registration, login, and a personalized student dashboard. This system also includes options for students to opt into email notifications and stores all data in structured text files.

## Features

### 1. Student Registration
- **Registration Form**: Students register by filling out a form with details like first name, last name, phone number, email, course, specialization, and password.
- **Opt-In Notifications**: An optional checkbox allows students to subscribe to notifications; if selected, their email is stored separately.
- **Data Storage**: Registration data is securely stored in a CSV-like format (`StudentDetails.txt`), with the password hidden.
- **Validation**: Email and password inputs are validated to ensure correctness and security.

### 2. Student Login
- **Login Access**: Students log in with their mobile number and password.
- **Verification**: Login credentials are verified by checking `StudentDetails.txt` for matching entries.
- **Student Dashboard**: Upon successful login, students are redirected to a personalized dashboard.

### 3. Student Dashboard
- **Read-Only Data Display**: Displays student details (Name, Mobile Number, Email, etc.) in a read-only format, with the password masked as "******" for security.
- **Data Protection**: All fields are non-editable, ensuring that student data remains secure.

### 4. Email Notifications
- **Subscription List**: Students opting into notifications have their emails stored in a separate file, `Email.txt`.
- **Notification Management**: `Email.txt` serves as a reference for students who have consented to receive updates.

## Project Structure

### File Organization
- **StudentDetails.txt**: Stores all student registration data in a comma-separated format:
  ```
  John,Doe,9234567890,john.doe@example.com,B.Tech,CSE,secret123
  ```
- **Email.txt**: Contains emails of students who have opted for notifications.

### Workflow Overview

#### Registration Process
1. Students fill out the registration form and submit.
2. Data validation is conducted:
   - **Email Validation**: Uses regex to ensure email format accuracy.
   - **Password Validation**: Enforces specific criteria (length, special characters, etc.).
3. If notifications are selected, the email is added to `Email.txt`.
4. Successful registrations are saved to `StudentDetails.txt`, and the form is reset.

#### Login Process
1. Students log in with their mobile number and password.
2. Credentials are verified against entries in `StudentDetails.txt`.
3. On successful login, students are redirected to their Student Dashboard.

#### Dashboard Display
- The dashboard shows student details in a vertical, read-only format.
- Password is hidden and displayed as "******" to protect sensitive information.

## Technical Components

- **Java Swing**: Provides GUI components (forms, buttons, labels, etc.).
- **File Handling**: Utilizes Java’s `BufferedWriter` and `BufferedReader` for text file management.
- **Regex**: Validates email format and ensures proper input data.
- **Input Validation**: Checks for valid mobile numbers and passwords to maintain data integrity.

## Future Enhancements (Optional)

- **Admin Dashboard**: Implement an interface for administrators to view and manage student records.
- **Password Encryption**: Enhance security by encrypting passwords before storage.
- **Database Migration**: Consider transitioning to SQLite or MySQL for better scalability and performance.

## Summary

The University Admission & Maintenance System currently enables:
- Student registration and secure login.
- Personalized dashboards with protected data display.
- Collection of notification preferences and secure data storage in text files.

Future extensions could include an admin interface, encrypted passwords, and a shift to a more robust database for scalability.

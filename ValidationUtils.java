package maintenance;

public class ValidationUtils {

    // Method to validate phone number
    public static boolean isValidPhoneNumber(String phone) {
        // Check if the phone number is exactly 10 characters long
        if (phone.length() != 10) {
            return false; // Invalid if not 10 characters
        }

        // Check if all characters are digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false; // Invalid if any character is not a digit
            }
        }

        return true; // Valid phone number
    }

    // Method to validate password
    public static boolean isValidPassword(String password) {
        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            return false; // Invalid if shorter than 8 characters
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // Check for upper case, lower case, digit, and special character
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if ("@#$%^&+=!".indexOf(ch) >= 0) { // Check for special characters
                hasSpecialChar = true;
            }
        }

        // Return true only if all conditions are met
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}
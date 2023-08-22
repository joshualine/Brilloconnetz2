package org.brilloconnectz2;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Validations {
    public static String validateUsername(String username) {
        if (username.isEmpty() || username.length() < 4) {
            return "Username must not be empty and must have at least 4 characters.";
        }
        return null;
    }

    public static String validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!email.isEmpty() && !matcher.matches()) {
            return "Email must not be empty and must be a valid email address.";
        }
        return null;
    }

    public static String validatePassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&*])(?=\\S+$).{8,}$";
        if (!password.isEmpty() && !password.matches(passwordRegex)) {
            return "Password must not be empty, and must be strong (1 uppercase, 1 special character, 1 number, 8 characters minimum).";
        }
        return null;
    }

    public static String validateDOB(String dobString) {
        try {
            LocalDate dob = LocalDate.parse(dobString);
            LocalDate minAgeDate = LocalDate.now().minusYears(16);
            if (!dob.isBefore(minAgeDate)) {
                return "Date of Birth must not be empty and the person should be at least 16 years old.";
            }
        } catch (DateTimeParseException e) {
            return "Date of Birth must be in the format yyyy-MM-dd.";
        }
        return null;
    }
}

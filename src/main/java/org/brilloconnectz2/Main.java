package org.brilloconnectz2;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeParseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        // Collect input and validate...

        String username = collectInput("Username: ", scanner);
        String email = collectInput("Email: ", scanner);
        String password = collectInput("Password: ", scanner);
        String dobString = collectInput("Date of Birth (yyyy-MM-dd): ", scanner);

        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NodWEiLCJpYXQiOjE2OTI3MjEwNTIsImV4cCI6MTY5MjcyNDY1Mn0.JDl6NVBjzmyGykJoiYdIzuhFhpWAAJ7fYyQ_fllbBTQ\n";
        String verificationStatus = verifyJWT(jwtToken);
        System.out.println("Verification status: " + verificationStatus);

        List<String> validationFailures = validateInputs(username, email, password, dobString);

        if (validationFailures.isEmpty()) {
            // Generate and return the JWT
            String jwt = generateJWT(username);
            System.out.println("JWT Token: " + jwt);
        } else {
            System.out.println("Validation failures:");
            for (String failure : validationFailures) {
                System.out.println("- " + failure);
            }
        }

        if (allCriteriaMet(username, email, password, dobString)) {
            // Generate and return the JWT
            String jwt = generateJWT(username);
            System.out.println("JWT Token: " + jwt);
        } else {
            // Handle validation failures...
        }
    }

    public static boolean allCriteriaMet(String username, String email, String password, String dobString) {
        List<String> validationFailures = validateInputs(username, email, password, dobString);
        return validationFailures.isEmpty();
    }


    // ... Validation and other methods ...
    public static String collectInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static List<String> validateInputs(String username, String email, String password, String dobString) {
        List<String> validationFailures = new ArrayList<>();

        // Validate and add failures to the list
        if (username.isEmpty() || username.length() < 4) {
            validationFailures.add("Username must not be empty and must have at least 4 characters.");
        }

        if (!isValidEmail(email)) {
            validationFailures.add("Email must not be empty and must be a valid email address.");
        }

        if (!isValidPassword(password)) {
            validationFailures.add("Password must not be empty, and must be strong (1 uppercase, 1 special character, 1 number, 8 characters minimum).");
        }
//
        if (!isValidDOB(dobString)) {
            validationFailures.add("Date of Birth must not be empty and the person should be at least 16 years old.");
        }

        return validationFailures;
    }

    private static boolean isValidDOB(String dobString) {
        try {
            LocalDate dob = LocalDate.parse(dobString);
            LocalDate minAgeDate = LocalDate.now().minusYears(16);
            return !dobString.isEmpty() && dob.isBefore(minAgeDate);
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }

    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&*])(?=\\S+$).{8,}$";
        return !password.isEmpty() && password.matches(passwordRegex);
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return !email.isEmpty() && matcher.matches();
    }

    public static String generateJWT(String username) {
        // Replace with your actual secret key
        String secretKey = "your&**&()(&&^%GGGGGJHFF((-secret-key";

        // Generate the JWT
        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(1)))) // Expires in 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        return jwt;
    }

    public static String verifyJWT(String jwtToken) {
        // Replace with your actual secret key
        String secretKey = "your&**&()(&&^%GGGGGJHFF((-secret-key";

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(jwtToken);

            // Successfully verified
            return "verification pass";
        } catch (Exception e) {
            // Verification failed
            return "verification fails";
        }
    }
}
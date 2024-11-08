package hospital;

import java.util.Scanner;

public class LoginPage {
    private HospitalManagementSystem hms;

    public LoginPage(HospitalManagementSystem hms) {
        this.hms = hms;
    }

    public User start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hospital Management System");

        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = hms.login(userID, password);

        if (user != null) {
            System.out.println("Login successful!");
            return user;  // Return the logged-in user
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return null;  // Return null for unsuccessful login
        }
    }
}

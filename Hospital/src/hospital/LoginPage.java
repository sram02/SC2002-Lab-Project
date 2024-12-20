package hospital;

import java.util.Scanner;

public class LoginPage {
    private HospitalManagementSystem hms;

    public LoginPage(HospitalManagementSystem hms) {
        this.hms = hms;
    }

    public User start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = hms.login(userID, password);

        if (user != null) {
            System.out.println("Login successful!\n");
            return user;  // Return the logged-in user
        } else {
            System.out.println("Invalid credentials. Please try again.\n");
            return null;  // Return null for unsuccessful login
        }
    }
}

package hospital;

import java.util.Scanner;

public class DoctorMenu {
    private Doctor doctor;

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome, Dr. " + doctor.getName() + "! This is the Doctor Menu.");
            System.out.println("1. View Patient Records");
            System.out.println("2. Update Patient Records");
            System.out.println("3. View Schedule");
            System.out.println("4. Set Availability");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            switch (choice) {
                case 1:
                    // Call method to view patient records
                    System.out.println("Viewing patient records...");
                    break;
                case 2:
                    // Call method to update patient records
                    System.out.println("Updating patient records...");
                    break;
                case 3:
                    // Call method to view schedule
                    System.out.println("Viewing schedule...");
                    break;
                case 4:
                    // Call method to set availability
                    System.out.println("Setting availability...");
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;  // Exit the menu and go back to the main login screen
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add space for readability
        }
    }
}

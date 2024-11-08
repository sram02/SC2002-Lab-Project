package hospital;

import java.util.Scanner;

public class PharmacistMenu {
    private Pharmacist pharmacist;
    private HospitalManagementSystem hms;

    public PharmacistMenu(Pharmacist pharmacist, HospitalManagementSystem hms) {
        this.pharmacist = pharmacist;
        this.hms = hms;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    pharmacist.viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    System.out.println("Updating prescription status...");
                    // Code for updating prescription status
                    break;
                case 3:
                    pharmacist.monitorInventory();
                    break;
                case 4:
                    System.out.println("Submitting replenishment request...");
                    // Code for replenishment request
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}

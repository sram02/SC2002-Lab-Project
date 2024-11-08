package hospital;

import java.util.Scanner;

public class PatientMenu {
    private Patient patient;
    private HospitalManagementSystem hms;

    public PatientMenu(Patient patient, HospitalManagementSystem hms) {
        this.patient = patient;
        this.hms = hms;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPatient Menu:");
            System.out.println("1. View Medical Record");
            System.out.println("2. View Scheduled Appointments");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    patient.getRecordManager().viewRecords();
                    break;
                case 2:
                    patient.viewScheduledAppointments();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}

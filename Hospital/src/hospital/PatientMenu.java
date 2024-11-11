package hospital;

import java.util.Scanner;

public class PatientMenu {
    private Patient patient;

    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome, " + patient.getName() + "! This is the Patient Menu.");
        
        while (isRunning) {
            System.out.println("1. View Medical Record");
            System.out.println("2. Schedule an Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Reschedule Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View Medical Record
                    patient.viewMedicalRecord();
                    break;
                case 2:
                    // Schedule an Appointment
                    System.out.println("Scheduling an appointment...");
                    // Add scheduling logic here
                    break;
                case 3:
                    // View Appointments
                    patient.viewAppointments();
                    break;
                case 4:
                    // Reschedule Appointment
                    System.out.println("Rescheduling an appointment...");
                    // Add rescheduling logic here
                    break;
                case 5:
                    // Cancel Appointment
                    System.out.println("Cancelling an appointment...");
                    // Add cancellation logic here
                    break;
                case 6:
                    // Logout
                    System.out.println("Logging out...");
                    isRunning = false;  // Exit the loop to go back to the main login prompt
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();  // Print a blank line for spacing
        }
    }
}

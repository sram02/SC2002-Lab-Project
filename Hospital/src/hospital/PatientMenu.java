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
            // Display the menu
            System.out.println("1. View Medical Record");
            System.out.println("2. Update non-medical personal information");
            System.out.println("3. View and schedule an Appointment");
            System.out.println("4. Reschedule Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. View status of scheduled appointments");
            System.out.println("7. View Appointment Outcome Records");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View Medical Record
                    patient.toString();
                    patient.get_RM().view_diagoses_treatments();
                    break;
                case 2:
                    // Update non-medical personal information (except blood type)
                    System.out.println("Updating non-medical personal information...");
                    patient.get_RM().updatePersonalInfo(patient, scanner);
                    break;
                case 3:
                    // Schedule an Appointment
                    System.out.println("View and Schedule an appointment...");
                    patient.get_PAM().schedule(patient.getUserID());
                    break;
                case 4:
                    // Reschedule Appointment
                    System.out.println("Rescheduling an appointment...");
                    patient.get_PAM().reschedule(patient.getUserID());
                    break;
                case 5:
                    // Cancel Appointment
                    System.out.println("Cancelling an appointment...");
                    patient.get_PAM().cancel();
                    break;
                case 6:
                    // View Status of Scheduled Appointments
                    System.out.println("Viewing status of scheduled appointments...");
                    patient.get_PAM().viewAppointments(patient.get_RM());
                    break;
                case 7:
                    // View Appointment Outcome Records
                    System.out.println("Viewing appointment outcome records...");
                    patient.get_RM().viewAppointmentOutcomes();
                    break;
                case 8:
                    // Logout
                    System.out.println("Logging out...");
                    isRunning = false;  // Exit the loop to go back to the main login prompt
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();  // Print a blank line for spacing
        }
    }
}

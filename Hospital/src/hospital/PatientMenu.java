package hospital;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientMenu {
    private Patient patient;
    private Scanner scanner;

    public PatientMenu(Patient patient, Scanner scanner) {
        this.patient = patient;
        this.scanner = scanner;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome, " + patient.getName() + "! This is the Patient Menu.");
        
        while (isRunning) {
            try {
                // Display the menu
                System.out.println("1. View Medical Record");
                System.out.println("2. Update non-medical personal information");
                System.out.println("3. View and schedule an Appointment");
                System.out.println("4. Reschedule Appointment");
                System.out.println("5. Cancel Appointment");
                System.out.println("6. View status of scheduled appointments");
                System.out.println("7. View Appointment Outcome Records");
                System.out.println("8. Change Password");
                System.out.println("9. Logout");
                System.out.print("Enter your choice: ");

                int choice = getChoice();

                switch (choice) {
                    case 1:
                        // View Medical Record
                        System.out.println(patient.toString());
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
                        patient.get_PAM().schedule(patient);
                        break;
                    case 4:
                        // Reschedule Appointment
                        System.out.println("Rescheduling an appointment...");
                        patient.get_PAM().reschedule(patient);
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
                        changePassword();
                        break;
                    case 9:
                        // Logout
                        System.out.println("Logging out...");
                        isRunning = false;  // Exit the loop to go back to the main login prompt

                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 8.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println();  // Print a blank line for spacing
        }

        // Close the scanner when done
    }
    private void changePassword() {
        System.out.println("Changing password...");
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        this.patient.setPassword(password);
        System.out.println("Password changed successfully.");
    }
    private int getChoice() {
        int choice = -1;
        while (choice < 1 || choice > 9) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        }
        return choice;
    }
}

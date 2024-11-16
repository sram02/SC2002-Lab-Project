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
            System.out.println("3. View available doctor slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. View status of scheduled appointments");
            System.out.println("8. View Appointment Outcome Records");
            System.out.println("9. Logout");
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
                    patient.updatePersonalInfo();
                    break;
                case 3:
                    // View Available Doctor Slots
                    System.out.println("Viewing available doctor slots...");
                    patient.viewDoctorSlots();
                    break;
                case 4:
                    // Schedule an Appointment
                    System.out.println("Scheduling an appointment...");
                    patient.get_PAM().schedule(patient.getUserID());
                    break;
                case 5:
                    // Reschedule Appointment
                    System.out.println("Rescheduling an appointment...");
                    patient.rescheduleAppointment();
                    break;
                case 6:
                    // Cancel Appointment
                    System.out.println("Cancelling an appointment...");
                    patient.cancelAppointment();
                    break;
                case 7:
                    // View Status of Scheduled Appointments
                    System.out.println("Viewing status of scheduled appointments...");
                    patient.get_PAM().viewAppointments(patient.get_RM());
                    break;
                case 8:
                    // View Appointment Outcome Records
                    System.out.println("Viewing appointment outcome records...");
                    patient.get_PAM().viewAppointmentOutcomes(patient.get_RM());
                    break;
                case 9:
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

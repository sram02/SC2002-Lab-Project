package hospital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PatientMenu {
    private Patient patient;
    private PAppointmentManager patientAppointmentManager; // Reference to PAppointmentManager
    private DAppointmentManager doctorAppointmentManager; // Reference to DAppointmentManager

    public PatientMenu(Patient patient, PAppointmentManager patientAppointmentManager, DAppointmentManager doctorAppointmentManager) {
        this.patient = patient;
        this.patientAppointmentManager = patientAppointmentManager;
        this.doctorAppointmentManager = doctorAppointmentManager;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome, " + patient.getName() + "! This is the Patient Menu.");
        
        while (isRunning) {
            System.out.println("1. View Medical Record");
            System.out.println("2. Schedule an Appointment");
            System.out.println("3. View Upcoming Appointments");
            System.out.println("4. Reschedule Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View Medical Record
                    patient.viewMedicalRecord();
                    break;
                case 2:
                    // Schedule an Appointment
                    System.out.println("Scheduling an appointment...");
                    System.out.print("Enter the doctor ID to schedule with: ");
                    String doctorID = scanner.nextLine();
                    patientAppointmentManager.schedule(patient.getUserID());
                    break;
                case 3:
                    // View Upcoming Appointments
                    System.out.println("Viewing your upcoming appointments...");
                    patientAppointmentManager.viewAppointments();
                    break;
                case 4:
                    // Reschedule Appointment
                    System.out.println("Rescheduling an appointment...");
                    System.out.print("Enter the doctor ID of the appointment: ");
                    doctorID = scanner.nextLine();
                    System.out.print("Enter the current appointment date (yyyy-MM-dd): ");
                    LocalDate currentDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter the current appointment time (HH:MM): ");
                    LocalTime currentTime = LocalTime.parse(scanner.nextLine());
                    System.out.println("Selecting a new appointment slot...");
                    patientAppointmentManager.cancel(patient.getUserID(), getDate(), getTime());
                    patientAppointmentManager.schedule(patient.getUserID());
                    break;
                case 5:
                    // Cancel Appointment
                    System.out.println("Cancelling an appointment...");
                    System.out.print("Enter the doctor ID of the appointment: ");
                    doctorID = scanner.nextLine();
                    System.out.print("Enter the appointment date (yyyy-MM-dd): ");
                    LocalDate cancelDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter the appointment time (HH:MM): ");
                    LocalTime cancelTime = LocalTime.parse(scanner.nextLine());
                    patientAppointmentManager.cancel(patient.getUserID(), getDate(), getTime());
                    break;
                case 6:
                    // Change Password
                    patient.changePassword(scanner);

                    break;
                case 7:
                    // Logout
                    System.out.println("Logging out...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Print a blank line for spacing
        }
    }
}

                    

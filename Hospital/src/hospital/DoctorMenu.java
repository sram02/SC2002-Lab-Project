package hospital;

import java.util.Scanner;

public class DoctorMenu {
    private Doctor doctor;
    private HospitalManagementSystem hms;

    public DoctorMenu(Doctor doctor, HospitalManagementSystem hms) {
        this.doctor = doctor;
        this.hms = hms;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. View Personal Schedule");
            System.out.println("3. Record Appointment Outcome");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID to view records: ");
                    String patientID = scanner.nextLine();
                    Patient patient = hms.getPatientByID(patientID);
                    doctor.viewPatientRecords(patient);
                    break;
                case 2:
                    doctor.viewSchedule();
                    break;
                case 3:
                    System.out.println("Recording appointment outcome...");
                    // Implement logic to create and add an AppointmentOutcomeRecord
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}

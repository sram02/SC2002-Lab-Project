package hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class DRecordManager {

    private ArrayList<Patient> patients; // For managing patient records

    // Constructor
    public DRecordManager() {
        this.patients = new ArrayList<>();
    }

    // Method to display patient records
    private void displayPatientInfo(Patient patient) {
        System.out.println("Patient name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getUserID());
        System.out.println("Blood type: " + patient.getBloodType());
    }

    // Method to display completed appointment records
    private void displayAppointmentInfo(Appointment appointment) {
        System.out.println("On " + appointment.getDate() + ", patient records show: ");
        System.out.println("Diagnosis: " + appointment.get_AOR().get_diagnosis());
        System.out.println("Treatment: " + appointment.get_AOR().get_treatment());
    }

    // Method to view patient's medical record
    public void viewPatientMedicalRecord(Scanner scanner) {
        int index = 0;
        for (Patient patient : patients) {
            System.out.println(index + ". ");
            displayPatientInfo(patient);
            index++;
            System.out.println();
        }

        System.out.println("Which patient's medical record would you like to see?: ");
        index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < patients.size()) {
            Patient item = patients.get(index);
            displayPatientInfo(item);

            // Display completed appointment records
            for (Appointment appointment : item.get_RM().getCompleted()) {
                displayAppointmentInfo(appointment);
            }
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    // Method to update patient's medical record
    public void UpdatePatientRecord(Scanner scanner) {
        int index = 0;
        for (Patient patient : patients) {
            System.out.println(index + ". ");
            displayPatientInfo(patient);
            index++;
            System.out.println();
        }

        System.out.println("Which patient's medical record would you like to update?: ");
        index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < patients.size()) {
            Patient item = patients.get(index);
            index = 0;

            // Display completed appointments for editing
            for (Appointment appointment : item.get_RM().getCompleted()) {
                System.out.println(index + ". ");
                displayAppointmentInfo(appointment);
                index++;
            }

            System.out.println("Which record would you like to edit?");
            index = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < item.get_RM().getCompleted().size()) {
                AppointmentOutcomeRecord record = item.get_RM().getCompleted().get(index);
                updateDiagnosis(scanner, record);
                updateTreatment(scanner, record);
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    // Method to update the diagnosis of a completed appointment
    private void updateDiagnosis(Scanner scanner, AppointmentOutcomeRecord record) {
        System.out.println("Would you like to edit diagnosis? (Y/N)");
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("Y")) {
            System.out.println("Enter new diagnosis: ");
            String newInput = scanner.nextLine();
            record.set_diagnosis(newInput);
        }
    }

    // Method to update the treatment of a completed appointment
    private void updateTreatment(Scanner scanner, AppointmentOutcomeRecord record) {
        System.out.println("Would you like to edit treatment? (Y/N)");
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("Y")) {
            System.out.println("Enter new treatment: ");
            String newInput = scanner.nextLine();
            record.set_treatment(newInput);
        }
    }
}

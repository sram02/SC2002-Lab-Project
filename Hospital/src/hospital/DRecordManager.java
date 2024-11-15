package hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class DRecordManager {

    private ArrayList<Patient> patients; // For upcoming appointment classes which are set as

    // Constructor
    public DRecordManager() {
        this.patients = new ArrayList<>();
    }

    public void viewPatientMedicalRecord() {
        try (Scanner scanner = new Scanner(System.in)) { // Create Scanner inside the method
            int index = 0;
            for (Patient patient : patients) {
                System.out.println(index + ". ");
                System.out.println("Patient name: " + patient.getName());
                System.out.println("Patient ID: " + patient.getUserID());
                index++;
                System.out.println();
            }

            System.out.println("Which patient's medical record would you like to see?: ");
            index = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Patient item = patients.get(index);
            System.out.println("Patient name: " + item.getName());
            System.out.println("Patient ID: " + item.getUserID());
            System.out.println("Blood type: " + item.getBloodType());

            for (Appointment appointment : item.get_RM().getCompleted()) {
                System.out.println("On " + appointment.getDate() + ", patient records show: ");
                System.out.println("Diagnosis: " + appointment.get_AOR().get_diagnosis());
                System.out.println("Treatment: " + appointment.get_AOR().get_treatment());
            }
        } // Scanner is automatically closed here
    }

    public void UpdatePatientRecord() {
        try (Scanner scanner = new Scanner(System.in)) { // Create Scanner inside the method
            int index = 0;
            for (Patient patient : patients) {
                System.out.println(index + ". ");
                System.out.println("Patient name: " + patient.getName());
                System.out.println("Patient ID: " + patient.getUserID());
                System.out.println();
                index++;
            }

            System.out.println("Which patient's medical record would you like to update?: ");
            index = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Patient item = patients.get(index);

            index = 0;
            for (Appointment appointment : item.get_RM().getCompleted()) {
                System.out.println(index + ". ");
                System.out.println("On " + appointment.getDate() + ", patient records show: ");
                System.out.println("Diagnosis: " + appointment.get_AOR().get_diagnosis());
                System.out.println("Treatment: " + appointment.get_AOR().get_treatment());
                index++;
            }

            System.out.println("Which record would you like to edit?");
            index = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            AppointmentOutcomeRecord record = item.get_RM().getCompleted().get(index);

            System.out.println("Would you like to edit diagnosis? (Y/N)");
            String input, newInput;

            while (true) {
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("Y")) {
                    System.out.println("Enter new diagnosis: ");
                    newInput = scanner.nextLine();
                    record.set_diagnosis(newInput);
                    break;

                } else if (input.equals("N")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }

            System.out.println("Would you like to edit treatment? (Y/N)");
            while (true) {
                input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("Y")) {
                    System.out.println("Enter new treatment: ");
                    newInput = scanner.nextLine();
                    record.set_treatment(newInput);
                    break;

                } else if (input.equals("N")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        } 
    }
}

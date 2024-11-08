package hospital;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    private List<Medicine> medicines = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Staff> staff = new ArrayList<>();

    public Patient getPatientByID(String patientID) {
        for (Patient patient : patients) {
            if (patient.getUserID().equals(patientID)) {
                return patient;
            }
        }
        return null; // Return null if not found
    }
    
    // Load medicines from Medicine_List.csv
    public void loadPatients() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Patient_List.csv"));
        scanner.nextLine(); // Skip header
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            String patientID = data[0].trim();
            String name = data[1].trim();
            String dateOfBirth = data[2].trim();
            String gender = data[3].trim();
            String bloodType = data[4].trim();
            String contactInformation = data[5].trim(); // Email address

            patients.add(new Patient(patientID, name, gender, dateOfBirth, contactInformation, bloodType));
        }
        scanner.close();
    }

    public void loadStaff() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Staff_List.csv"));
        scanner.nextLine(); // Skip header
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            String userID = data[0].trim();
            String name = data[1].trim();
            StaffRole role = StaffRole.valueOf(data[2].trim().toUpperCase());
            String gender = data[3].trim();
            int age = Integer.parseInt(data[4].trim());
            if (role == StaffRole.DOCTOR) {
                staff.add(new Doctor(userID, name, gender, age));
            } else if (role == StaffRole.PHARMACIST) {
                staff.add(new Pharmacist(userID, name, gender, age));
            } else if (role == StaffRole.ADMINISTRATOR) {
                staff.add(new Administrator(userID, name, gender, age));
            }
        }
        scanner.close();
    }

    public void loadMedicines() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Medicine_List.csv"));
        scanner.nextLine(); // Skip header
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            String name = data[0].trim();
            int stockLevel = Integer.parseInt(data[1].trim());
            int lowStockThreshold = Integer.parseInt(data[2].trim());
            medicines.add(new Medicine(name, stockLevel, lowStockThreshold));
        }
        scanner.close();
    }
    public User login(String userID, String password) {
        for (Patient patient : patients) {
            if (patient.getUserID().equals(userID) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        for (Staff staffMember : staff) {
            if (staffMember.getUserID().equals(userID) && staffMember.getPassword().equals(password)) {
                return staffMember;
            }
        }
        System.out.println("Invalid login credentials.");
        return null;
    }


}

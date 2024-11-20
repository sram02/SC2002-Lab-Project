package hospital;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class StaffManager {
    private static List<Staff> staffList = new ArrayList<>(); // Initialize the list
    private String staffCsvFilePath;

    // Singleton instance
    private static StaffManager instance;

    // Private constructor to prevent instantiation
    private StaffManager(String staffCsvFilePath) {
        this.staffCsvFilePath = staffCsvFilePath;
        loadStaffFromFile();
    }
    
    private void loadStaffFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(staffCsvFilePath))) {
            String line = br.readLine(); // Skip the header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) { // Ensure correct number of columns
                    String userID = values[0].trim();
                    String name = values[1].trim();
                    String role = values[2].trim().toUpperCase();
                    String gender = values[3].trim();
                    int age = Integer.parseInt(values[4].trim());

                    Staff staff;
                    switch (role) {
                        case "DOCTOR":
                            staff = new Doctor(userID, "password", name, gender, age);
                            break;
                        case "PHARMACIST":
                            staff = new Pharmacist(userID, "password", name, gender, age, new InventoryManager(HospitalManagementSystem.getInventory()));
                            break;
                        case "ADMINISTRATOR":
                            staff = new Administrator(userID, "password", name, gender, age, HospitalManagementSystem.getAdminInventoryManager());
                            break;
                        default:
                            System.out.println("Unknown role: " + role + " for userID: " + userID);
                            continue; // Skip invalid roles
                    }
                    staffList.add(staff);
                }
            }
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading staff from file: " + e.getMessage());
        }
    }


    // Public static method to get the instance of the class
    public static StaffManager getInstance(String staffCsvFilePath) {
        if (instance == null) {
            instance = new StaffManager(staffCsvFilePath);
        }
        return instance;
    }

    // Method to get all staff
    public List<Staff> getAllStaff() {
        return new ArrayList<>(staffList); // Return a copy of the list to avoid external modification
    }

    // Method to view all staff with specific roles
    public void viewStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff available.");
        } else {
            for (Staff staff : staffList) {
                System.out.println("Staff ID: " + staff.getUserID() +
                        ", Name: " + staff.getName() +
                        ", Role: " + staff.getStaffRole().name() +
                        ", Gender: " + staff.getGender() +
                        ", Age: " + staff.getAge());
            }
        }
    }

    // View staff by role
    public void viewStaffByRole(StaffRole role) {
        boolean found = false;
        for (Staff staff : staffList) {
            if (staff.getStaffRole() == role) { // Use getStaffRole() to access StaffRole
                System.out.println("Staff ID: " + staff.getUserID() +
                        ", Name: " + staff.getName() +
                        ", Role: " + staff.getStaffRole().name() +
                        ", Gender: " + staff.getGender() +
                        ", Age: " + staff.getAge());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No staff found with role: " + role);
        }
    }

    // Other methods for adding, updating, and removing staff
    public void addStaff(Staff staff) {
        staffList.add(staff);
        saveToFile();
    }

    public Staff getStaffById(String userID) {
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(userID)) {
                return staff;
            }
        }
        return null;
    }

    public void updateStaff(String userID, String name, String password, String gender) {
        Staff staff = getStaffById(userID);
        if (staff != null) {
            if (!name.isEmpty()) {
                staff.setName(name);
            }
            if (!password.isEmpty()) {
                staff.setPassword(password);
            }
            if (!gender.isEmpty()) {
                staff.setGender(gender);
            }
            saveToFile();
        }
    }

    public boolean removeStaff(String userID) {
        Staff staff = getStaffById(userID);
        if (staff != null) {
            staffList.remove(staff);
            saveToFile();
            return true;
        }
        return false;
    }

    // Method to save staff list to file
    private void saveToFile() {
        try (FileWriter writer = new FileWriter(staffCsvFilePath)) {
            writer.write("UserID,Name,Role,Gender,Age\n");
            for (Staff staff : staffList) {
                String roleFormatted = staff.getStaffRole().name().charAt(0) + staff.getStaffRole().name().substring(1).toLowerCase();
                writer.write(staff.getUserID() + "," + staff.getName() + "," + roleFormatted + "," + staff.getGender() + "," + staff.getAge() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving staff to file: " + e.getMessage());
        }
    }
}

package hospital;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private List<Staff> staffList;
    private String staffCsvFilePath;

    public StaffManager(String staffCsvFilePath) {
        this.staffList = new ArrayList<>();
        this.staffCsvFilePath = staffCsvFilePath;
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

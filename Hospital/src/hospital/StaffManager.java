package hospital;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private List<Staff> staffList;

    public StaffManager() {
        this.staffList = new ArrayList<>();
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
        }
    }

    public boolean removeStaff(String userID) {
        Staff staff = getStaffById(userID);
        if (staff != null) {
            staffList.remove(staff);
            return true;
        }
        return false;
    }
}

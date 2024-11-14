package hospital;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
// import java.util.Iterator;
import java.util.InputMismatchException;

public class AdministratorMenu {
    private Administrator administrator;
    private StaffManager staffManager;
    private AdminInventoryManager adminInventoryManager;
    private HospitalManagementSystem hms; 
    private Scanner scanner;

    public AdministratorMenu(Administrator administrator, StaffManager staffManager, AdminInventoryManager adminInventoryManager, HospitalManagementSystem hms) {
        this.administrator = administrator;
        this.staffManager = staffManager;
        this.adminInventoryManager = adminInventoryManager;
        this.hms = hms; 
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Welcome, " + administrator.getName() + "! This is the Administrator Menu.");
        while (true) {
            System.out.println("1. Manage Staff");
            System.out.println("2. View Appointments");
            System.out.println("3. Manage Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Change Password");
            System.out.println("6. Logout \n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageStaff();
                    break;
                case 2:
                    // View Appointments functionality
                    break;
                case 3:
                	manageInventory();
                    break;
                case 4:
                    viewAndApproveRequests(); 
                    break;
                case 5:
                    administrator.changePassword(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageStaff() {
        while (true) {
            System.out.println("Manage Staff:");
            System.out.println("1. View Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Return to Main Menu \n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewStaff();
                    break;
                case 2:
                    addStaff();
                    break;
                case 3:
                    updateStaff();
                    break;
                case 4:
                    removeStaff();
                    break;
                case 5:
                	System.out.println("Returning to Main Menu..\n");
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewStaff() {
        System.out.println("Viewing Staff:");
        staffManager.viewStaff();
        System.out.println("\nReturning to the Manage Staff menu...\n");
    }

    private void addStaff() {
        System.out.println("Adding New Staff:");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Role (DOCTOR, PHARMACIST, ADMINISTRATOR): ");
        String roleInput = scanner.nextLine().toUpperCase();
        
        StaffRole role;
        try {
            role = StaffRole.valueOf(roleInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role entered. Please enter a valid role (DOCTOR, PHARMACIST, ADMINISTRATOR).");
            return;
        }

        // Create staff
        Staff newStaff;
        switch (role) {
            case DOCTOR:
                newStaff = new Doctor(userID, password, name, gender, age);
                hms.addDoctor((Doctor) newStaff); 
                break;
            case PHARMACIST:
                InventoryManager inventoryManager = new InventoryManager(hms.getInventory());
                newStaff = new Pharmacist(userID, password, name, gender, age, inventoryManager);
                hms.addPharmacist((Pharmacist) newStaff); 
                break;
            case ADMINISTRATOR:
                AdminInventoryManager adminInventoryManager = hms.getAdminInventoryManager();
                newStaff = new Administrator(userID, password, name, gender, age, adminInventoryManager);
                hms.addAdministrator((Administrator) newStaff); 
                break;
            default:
                System.out.println("Invalid role. Staff not added.");
                return;
        }

        staffManager.addStaff(newStaff);
        System.out.println("Staff added successfully.\n");
    }

    private void updateStaff() {
        System.out.print("Enter User ID of staff to update: ");
        String userID = scanner.nextLine();
        Staff staff = staffManager.getStaffById(userID);

        if (staff == null) {
            System.out.println("Staff not found.\n");
            return;
        }

        System.out.print("Enter New Name (Leave blank to keep current name): ");
        String name = scanner.nextLine();
        System.out.print("Enter New Password (Leave blank to keep current password): ");
        String password = scanner.nextLine();
        System.out.print("Enter Gender (Leave blank to keep current gender): ");
        String gender = scanner.nextLine();

        staffManager.updateStaff(userID, name, password, gender);
        System.out.println("Staff updated successfully.\n");
    }

    private void removeStaff() {
        System.out.print("Enter User ID of staff to remove: ");
        String userID = scanner.nextLine();
        if (staffManager.removeStaff(userID)) {
            System.out.println("Staff removed successfully.\n");
        } else {
            System.out.println("Staff not found.\n");
        }
    }
    
    private void manageInventory() {
        while (true) {
            System.out.println("Inventory Management:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Medicine");
            System.out.println("3. Update Medicine Stock");
            System.out.println("4. Update Low Stock Threshold");
            System.out.println("5. Remove Medicine");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminInventoryManager.viewInventory();
                    break;
                case 2:
                    System.out.print("Enter medicine name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter low stock threshold: ");
                    int threshold = scanner.nextInt();
                    scanner.nextLine(); 
                    adminInventoryManager.addMedicine(name, stock, threshold);
                    break;
                case 3:
                	 System.out.print("Enter medicine name: ");
                     String medName = scanner.nextLine();
                     
                     if (adminInventoryManager.getInventory().getMedicineByName(medName) == null) {
                         System.out.println("Invalid medicine. Please try again.\n");
                         break;
                     }
                     
                     System.out.print("Enter new stock level: ");
                     try {
                         int newStock = scanner.nextInt();
                         scanner.nextLine(); // Consume newline
                         adminInventoryManager.updateStockLevel(medName, newStock);
                     } catch (InputMismatchException e) {
                         System.out.println("Invalid input. Please enter a valid number for stock level.\n");
                         scanner.nextLine(); // Clear invalid input
                     }
                     break;
                case 4:
                	System.out.print("Enter medicine name: ");
                    String thresholdName = scanner.nextLine();
                    
                    if (adminInventoryManager.getInventory().getMedicineByName(thresholdName) == null) {
                        System.out.println("Invalid medicine. Please try again.\n");
                        break;
                    }
                    
                    System.out.print("Enter new low stock threshold: ");
                    try {
                        int newThreshold = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        adminInventoryManager.updateLowStockThreshold(thresholdName, newThreshold);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the low stock threshold.\n");
                        scanner.nextLine(); 
                    }
                    break;
                case 5:
                    System.out.print("Enter medicine name to remove: ");
                    String removeName = scanner.nextLine();
                    adminInventoryManager.removeMedicine(removeName);
                    break;
                case 6:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public void viewAndApproveRequests() {
        System.out.println("Pending Replenishment Requests:");

        // Temp list to hold requests to be removed after iteration
        List<ReplenishmentRequest> approvedRequests = new ArrayList<>();

        for (ReplenishmentRequest request : adminInventoryManager.getPendingRequests()) {
            System.out.println("Medicine: " + request.getMedicineName() + ", Quantity: " + request.getRequestedQuantity());
            System.out.print("Approve this request? (yes/no): ");
            String approval = scanner.nextLine();

            if (approval.equalsIgnoreCase("yes")) {
                adminInventoryManager.approveReplenishmentRequest(request);  // update stock
                approvedRequests.add(request);  
                System.out.println("Request approved for " + request.getMedicineName());
            }
        }
        
        adminInventoryManager.getPendingRequests().removeAll(approvedRequests);

        if (adminInventoryManager.getPendingRequests().isEmpty()) {
            System.out.println("No pending replenishment requests.");
        }
    }

}

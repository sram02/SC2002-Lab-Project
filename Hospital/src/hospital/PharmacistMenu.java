package hospital;

import java.util.Scanner;
import java.util.InputMismatchException;

public class PharmacistMenu {
    private Pharmacist pharmacist;
    private InventoryManager inventoryManager;
    private AdminInventoryManager adminInventoryManager;
    private Scanner scanner;

    public PharmacistMenu(Pharmacist pharmacist, InventoryManager inventoryManager, AdminInventoryManager adminInventoryManager) {
        this.pharmacist = pharmacist;
        this.inventoryManager = inventoryManager;
        this.adminInventoryManager = adminInventoryManager;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Welcome, " + pharmacist.getName() + "! This is the Pharmacist Menu.");
        while (true) {
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Change Password");
            System.out.println("6. Logout \n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    // View Appointment Outcome Record logic
                    break;
                case 2:
                    // Update Prescription Status logic
                    break;
                case 3:
                    pharmacist.viewInventory();
                    break;
                case 4:
                    System.out.print("Enter medicine name: ");
                    String medicineName = scanner.nextLine();

                    // Check if medicine exist in inventory
                    if (inventoryManager.getInventory().getMedicineByName(medicineName) == null) {
                        System.out.println("Invalid medicine. Please try again.\n");
                        break;
                    }

                    System.out.print("Enter requested quantity: ");
                    int quantity;
                    try {
                        quantity = scanner.nextInt();
                        scanner.nextLine();  
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid quantity. Please enter a valid number.");
                        scanner.nextLine(); 
                        break;
                    }

                    pharmacist.submitReplenishmentRequest(medicineName, quantity, adminInventoryManager);
                    break;
                case 5:
                    pharmacist.changePassword(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

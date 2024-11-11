package hospital;

import java.util.Scanner;

public class PharmacistMenu {
    private Pharmacist pharmacist;
    private InventoryManager inventoryManager;
    private AdminInventoryManager adminInventoryManager;  
    private Scanner scanner;

    public PharmacistMenu(Pharmacist pharmacist, InventoryManager inventoryManager, AdminInventoryManager adminInventoryManager) {
        this.pharmacist = pharmacist;
        this.inventoryManager = inventoryManager;
        this.adminInventoryManager = adminInventoryManager;  // Initialize adminInventoryManager
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Welcome, " + pharmacist.getName() + "! This is the Pharmacist Menu.");
        while (true) {
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

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
                    System.out.print("Enter requested quantity: ");
                    int quantity = scanner.nextInt();
                    pharmacist.submitReplenishmentRequest(medicineName, quantity, adminInventoryManager);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

package hospital;

import java.util.Scanner;

public class Pharmacist extends Staff {
    private InventoryManager inventoryManager; // Singleton class
    private static final AppointmentOutcomeManager AOM = AppointmentOutcomeManager.getInstance(); // Singleton class

    // Constructor
    public Pharmacist(String userID, String password, String name, String gender, int age, InventoryManager IM) {
        super(userID, password, name, gender, age, StaffRole.PHARMACIST);
        this.inventoryManager = IM;
        // AOM is already initialized as it's static and Singleton, no need to reinitialize
    }

    // Change password method
    public void changePassword(Scanner scanner) {
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if (!this.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect current password.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            this.setPassword(newPassword);
            System.out.println("Password changed successfully. \n");
        } else {
            System.out.println("Passwords do not match. Password change failed. \n");
        }
    }

    // Get the InventoryManager
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    // View the inventory
    public void viewInventory() {
        inventoryManager.viewInventory();
    }

    // Submit replenishment request
    public void submitReplenishmentRequest(String medicineName, int quantity, AdminInventoryManager adminInventoryManager) {
        // Check if the medicine exists in the inventory
        if (inventoryManager.getInventory().getMedicineByName(medicineName) == null) {
            System.out.println("Invalid medicine. Please try again.\n");
            return;
        }

        // If medicine exists, proceed with replenish request
        ReplenishmentRequest request = new ReplenishmentRequest(medicineName, quantity, this);
        inventoryManager.submitReplenishmentRequest(request, adminInventoryManager);
    }

    // Get the AppointmentOutcomeManager (Singleton)
    public AppointmentOutcomeManager getAOM() {
        return AOM;
    }
}

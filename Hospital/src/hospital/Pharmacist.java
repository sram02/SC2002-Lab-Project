package hospital;

import java.util.Scanner;

public class Pharmacist extends Staff {
    private InventoryManager inventoryManager;

    public Pharmacist(String userID, String password, String name, String gender, int age, InventoryManager inventoryManager) {
        super(userID, password, name, gender, age, StaffRole.PHARMACIST);
        this.inventoryManager = inventoryManager;
    }
    
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

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void viewInventory() {
        inventoryManager.viewInventory();
    }

    public void submitReplenishmentRequest(String medicineName, int quantity, AdminInventoryManager adminInventoryManager) {
        ReplenishmentRequest request = new ReplenishmentRequest(medicineName, quantity, this);
        inventoryManager.submitReplenishmentRequest(request, adminInventoryManager);
    }
}

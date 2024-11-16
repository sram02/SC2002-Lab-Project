package hospital;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InventoryManager {
    private Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean takeMedicine(String medName, int quantity) {
        Medicine medicine = this.inventory.getMedicineByName(medName);

        // Case 1: Medicine not found
        if (medicine == null) {
            System.out.println("Medicine '" + medName + "' not found in inventory.");
            return false;
        }

        // Case 2: Medicine is at the low stock level. Urge to submit request, but successfully taken medicine
        if (medicine.getStockLevel() - quantity < medicine.getLowStockThreshold()) {
            medicine.setStockLevel(medicine.getStockLevel() - quantity);
            System.out.println(medName + " quantity is now below low stock threshold. You are urged to submit a replenishment request.");
            this.saveInventoryToCSV();
            return true;
        }

        // Case 3: Medicine stock is not enough. Return false and do nothing
        if (medicine.getStockLevel() < quantity) {
            System.out.println("Stock level for '" + medName + "' is too low. Please request replenishment.");
            return false;
        }

        // Case 4: Medicine is enough. Successfully taken medicine
        medicine.setStockLevel(medicine.getStockLevel() - quantity);
        this.saveInventoryToCSV();
        return true;
    }

    // Save inventory data to CSV
    public void saveInventoryToCSV() {
        try (FileWriter writer = new FileWriter("Medicine_List.csv", false)) { // Overwrite the file each time
            writer.write("Name,StockLevel,LowStockThreshold\n"); // CSV Header
            for (Medicine medicine : inventory.getMedicines()) {
                writer.write(medicine.getName() + "," + medicine.getStockLevel() + "," + medicine.getLowStockThreshold() + "\n");
            }
            System.out.println("Inventory data saved to Medicine_List.csv");
        } catch (IOException e) {
            System.out.println("Error saving inventory data to CSV: " + e.getMessage());
        }
    }

    // View inventory list
    public void viewInventory() {
        System.out.println("\nInventory:");
        List<Medicine> medicines = inventory.getMedicines();

        if (medicines.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            for (Medicine medicine : medicines) {
                System.out.print("Medicine: " + medicine.getName() +
                        ", Stock Level: " + medicine.getStockLevel() +
                        ", Low Stock Alert: " + medicine.getLowStockThreshold());

                // Check if stock level is below threshold
                if (medicine.getStockLevel() < medicine.getLowStockThreshold()) {
                    System.out.print(" - WARNING: Stock level is low!");
                }
                System.out.println();
            }
        }

        System.out.println("\nReturning to Menu...\n");
    }

    // Submit replenishment request
    public void submitReplenishmentRequest(ReplenishmentRequest request, AdminInventoryManager adminInventoryManager) {
        adminInventoryManager.addReplenishmentRequest(request);
        System.out.println("Replenishment request submitted for: " + request.getMedicineName());
        System.out.println("\nReturning to Menu...\n");
    }
}

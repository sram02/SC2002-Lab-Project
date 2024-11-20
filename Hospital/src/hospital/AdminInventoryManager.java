package hospital;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdminInventoryManager {
    private Inventory inventory;
    private List<ReplenishmentRequest> pendingRequests;

    public AdminInventoryManager(Inventory inventory) {
        this.inventory = inventory;
        this.pendingRequests = new ArrayList<>();
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    // View all items in inventory
    public void viewInventory() {
        System.out.println("\nInventory:");
        for (Medicine medicine : inventory.getMedicines()) {
            System.out.println(medicine);
        }
        System.out.print("\nReturning to Inventory Management Menu..\n\n");
    }

    // Add new medicine to inventory
    public void addMedicine(String name, int initialStock, int lowStockThreshold) {
        Medicine newMedicine = new Medicine(name, initialStock, lowStockThreshold);
        inventory.addMedicine(newMedicine);
        saveInventoryToCSV();
        System.out.println("Medicine added: " + name);
        System.out.println("\nReturning to Inventory Management Menu..\n");
    }

    // Update stock level of existing medicine
    public void updateStockLevel(String name, int newStock) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            medicine.setStockLevel(newStock);
            saveInventoryToCSV();
            System.out.println("Stock level updated for " + name);
            System.out.println("\nReturning to Inventory Management Menu..\n");
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Update low stock alert threshold of existing medicine
    public void updateLowStockThreshold(String name, int newThreshold) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            medicine.setLowStockThreshold(newThreshold);
            saveInventoryToCSV();
            System.out.println("Low stock alert updated for " + name);
            System.out.println("\nReturning to Inventory Management Menu..\n");
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Remove medicine from inventory
    public void removeMedicine(String name) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            inventory.getMedicines().remove(medicine);
            System.out.println("Medicine removed: " + name);
            saveInventoryToCSV();
            System.out.println("\nReturning to Inventory Management Menu..\n");
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Replenishment request management
    public List<ReplenishmentRequest> getPendingRequests() {
        return pendingRequests;
    }

    public void addReplenishmentRequest(ReplenishmentRequest request) {
        pendingRequests.add(request);
        
    }

    public void approveReplenishmentRequest(ReplenishmentRequest request) {
        Medicine medicine = inventory.getMedicineByName(request.getMedicineName());
        if (medicine != null) {
            medicine.increaseStock(request.getRequestedQuantity());
            System.out.println("Replenishment approved for: " + request.getMedicineName());
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Remove a specific replenishment request
    public void removeReplenishmentRequest(ReplenishmentRequest request) {
        pendingRequests.remove(request);
    }
    
    public void saveInventoryToCSV() {
        try (FileWriter writer = new FileWriter("Medicine_List.csv")) {
            writer.write("Name,StockLevel,LowStockThreshold\n"); 
            for (Medicine medicine : inventory.getMedicines()) {
                writer.write(medicine.getName() + "," + medicine.getStockLevel() + "," + medicine.getLowStockThreshold() + "\n");
            }
            System.out.println("Inventory data saved to Medicine_List.csv");
        } catch (IOException e) {
            System.out.println("Error saving inventory data to CSV: " + e.getMessage());
        }
    }
}

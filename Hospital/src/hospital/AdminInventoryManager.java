package hospital;

import java.util.ArrayList;
import java.util.List;

public class AdminInventoryManager {
    private Inventory inventory;
    private List<ReplenishmentRequest> pendingRequests;

    public AdminInventoryManager(Inventory inventory) {
        this.inventory = inventory;
        this.pendingRequests = new ArrayList<>();
    }

    // View all items in inventory
    public void viewInventory() {
        System.out.println("\nInventory:");
        for (Medicine medicine : inventory.getMedicines()) {
            System.out.println(medicine);
        }
        System.out.print("\nReturning to Inventory Management Menu..\n\n");
    }

    // Add a new medicine to inventory
    public void addMedicine(String name, int initialStock, int lowStockThreshold) {
        Medicine newMedicine = new Medicine(name, initialStock, lowStockThreshold);
        inventory.addMedicine(newMedicine);
        System.out.println("Medicine added: " + name);
    }

    // Update the stock level of an existing medicine
    public void updateStockLevel(String name, int newStock) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            medicine.setStockLevel(newStock);
            System.out.println("Stock level updated for " + name);
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Update the low stock alert threshold of an existing medicine
    public void updateLowStockThreshold(String name, int newThreshold) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            medicine.setLowStockThreshold(newThreshold);
            System.out.println("Low stock alert updated for " + name);
        } else {
            System.out.println("Medicine not found in inventory.\n");
        }
    }

    // Remove a medicine from inventory
    public void removeMedicine(String name) {
        Medicine medicine = inventory.getMedicineByName(name);
        if (medicine != null) {
            inventory.getMedicines().remove(medicine);
            System.out.println("Medicine removed: " + name);
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
        System.out.println("Replenishment request added for: " + request.getMedicineName());
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
}

package hospital;

import java.util.List;

public class InventoryManager {
    private Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public Inventory getInventory() {
        return inventory;
    }

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


    public void submitReplenishmentRequest(ReplenishmentRequest request, AdminInventoryManager adminInventoryManager) {
        adminInventoryManager.addReplenishmentRequest(request);
        System.out.println("Replenishment request submitted for: " + request.getMedicineName());
        System.out.print("\nReturning to Menu..\n\n");
    }
}

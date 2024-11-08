package hospital;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Medicine> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        inventory.add(medicine);
    }

    public void viewInventory() {
        for (Medicine medicine : inventory) {
            System.out.println(medicine);
        }
    }

    public void updateMedicineStock(Medicine medicine, int newStockLevel) {
        medicine.setStockLevel(newStockLevel);
    }

    public void submitReplenishmentRequest(String medicineName) {
        // Logic to submit replenishment request
    }

    public void approveReplenishmentRequest(String medicineName) {
        // Logic to approve replenishment request
    }
}

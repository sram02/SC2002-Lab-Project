package hospital;

public class Pharmacist extends Staff {
    private InventoryManager inventoryManager;

    public Pharmacist(String userID, String password, String name, String gender, int age, InventoryManager inventoryManager) {
        super(userID, password, name, gender, age, StaffRole.PHARMACIST);
        this.inventoryManager = inventoryManager;
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

package hospital;

public class Administrator extends Staff {
    private InventoryManager inventoryManager;

    public Administrator(String userID, String name, String gender, int age) {
        super(userID, name, gender, StaffRole.ADMINISTRATOR, age);
        this.inventoryManager = new InventoryManager();
    }

    public void addStaffMember(Staff staff) {
        // Code to add a new staff member
    }

    public void updateStaffMember(Staff staff) {
        // Code to update an existing staff member's information
    }

    public void removeStaffMember(String staffID) {
        // Code to remove staff member
    }

    public void manageInventory(Medicine medicine, int newStockLevel) {
        inventoryManager.updateMedicineStock(medicine, newStockLevel);
    }

    public void approveReplenishmentRequest(String medicineName) {
        inventoryManager.approveReplenishmentRequest(medicineName);
    }

    public void viewInventory() {
        inventoryManager.viewInventory();
    }
}

package hospital;

public class Administrator extends Staff {
    private AdminInventoryManager inventoryManager;

    public Administrator(String userID, String password, String name, String gender, int age, AdminInventoryManager inventoryManager) {
        super(userID, password, name, gender, age, StaffRole.ADMINISTRATOR);
        this.inventoryManager = inventoryManager;
    }

    public AdminInventoryManager getInventoryManager() {
        return inventoryManager;
    }
}

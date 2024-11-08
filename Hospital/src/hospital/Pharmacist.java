package hospital;

public class Pharmacist extends Staff {
    private AppointmentOutcomeManager appointmentOutcomeManager;
    private InventoryManager inventoryManager;

    public Pharmacist(String userID, String name, String gender, int age) {
        super(userID, name, gender, StaffRole.PHARMACIST, age);
        this.appointmentOutcomeManager = new AppointmentOutcomeManager();
        this.inventoryManager = new InventoryManager();
    }

    public void viewAppointmentOutcomeRecord() {
        appointmentOutcomeManager.viewAppointmentOutcomeRecord();
    }

    public void updatePrescriptionStatus(AppointmentOutcomeRecord record, String status) {
        record.updateDispensedStatus(status);
    }

    public void monitorInventory() {
        inventoryManager.viewInventory();
    }

    public void submitReplenishmentRequest(String medicineName) {
        inventoryManager.submitReplenishmentRequest(medicineName);
    }
}

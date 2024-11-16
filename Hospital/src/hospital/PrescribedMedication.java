package hospital;

public class PrescribedMedication {
    private String name;
    private int quantity;
    private boolean dispensedStatus;

    // Constructor
    public PrescribedMedication(String name, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.name = name;
        this.quantity = quantity;
        this.dispensedStatus = false;  // Default to not dispensed
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isDispensedStatus() {
        return dispensedStatus;
    }

    // Method to mark the medication as dispensed
    public void dispense() {
        this.dispensedStatus = true;
    }

    // Optional: Override toString for easier printing and debugging
    @Override
    public String toString() {
        return "Medication Name: " + name + ", Quantity: " + quantity + ", Dispensed: " + dispensedStatus;
    }
}

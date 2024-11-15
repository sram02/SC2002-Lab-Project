package hospital;

public class PrescribedMedication {
    private String name;
    private int quantity;
    private boolean dispensedStatus;

    public PrescribedMedication(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.dispensedStatus = false;  // Default to not dispensed
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public boolean isDispensedStatus() { return dispensedStatus; }
    
    public void Dispense() {
    	this.dispensedStatus = true;
    }
}

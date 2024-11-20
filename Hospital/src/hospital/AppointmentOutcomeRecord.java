
package hospital;



public class AppointmentOutcomeRecord {
    private String diagnosis;
    private String treatment;
    private PrescribedMedication prescription;


    public AppointmentOutcomeRecord(String diagnosis, String treatment) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = null;
    }

    @Override
    public String toString() {
        System.out.println("AppointmentOutcomeRecord\n" + "diagnosis=" + diagnosis + ", treatment=" + treatment);
        System.out.println("Prescription name: " + prescription.getName());
        System.out.println("Prescription Quantity: " + prescription.getQuantity());
        if (prescription.isDispensedStatus()) {
        	System.out.println("Medicine has been dispensed.");
        }
        else {
        	System.out.println("Medicine has not yet been dispensed.");
        }
        return "\n";
    }
 
    public void set_prescription(String name, int quantity) {
    	this.prescription = new PrescribedMedication(name, quantity);
    }
    
    public String get_treatment() {
    	return this.treatment;
    }
    
    public void set_treatment(String treatment) {
    	this.treatment = treatment;
    }
    
    public String get_diagnosis() {
    	return this.diagnosis;
    }
    
    public void set_diagnosis(String diagnosis) {
    	this.diagnosis = diagnosis;
    }
    	
    public PrescribedMedication get_PM() {
    	return prescription;
    }
}

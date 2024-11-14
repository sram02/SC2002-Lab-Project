package hospital;

import java.util.Date;

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
        return "AppointmentOutcomeRecord{" + "diagnosis='" + diagnosis + '\'' + ", treatment='" + treatment + '\'' + ", dateOfAppointment=" + dateOfAppointment + ", prescription=" + prescription + ", bloodType='" + bloodType + '\'' + ", patientName='" + patientName + '\'' + ", doctorName='" + doctorName + '\'' + ", appointmentID=" + appointmentID + '}';
    }
    
    public void set_prescription(String name, int quantity) {
    	this.prescription = new PrescribedMedication(name, quantity);
    }
}

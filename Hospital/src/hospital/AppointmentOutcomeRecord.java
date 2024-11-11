package hospital;

import java.util.Date;

public class AppointmentOutcomeRecord {
    private String diagnosis;
    private String treatment;
    private Date dateOfAppointment;
    private PrescribedMedication prescription;
    private String bloodType;
    private String patientName;
    private String doctorName;
    private int appointmentID;

    public AppointmentOutcomeRecord(String diagnosis, String treatment, Date dateOfAppointment, PrescribedMedication prescription, String bloodType, String patientName, String doctorName, int appointmentID) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dateOfAppointment = dateOfAppointment;
        this.prescription = prescription;
        this.bloodType = bloodType;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentID = appointmentID;
    }

    public void setStatus(String status) {
        // Set prescription status or additional outcome updates as needed
    }

    @Override
    public String toString() {
        return "AppointmentOutcomeRecord{" + "diagnosis='" + diagnosis + '\'' + ", treatment='" + treatment + '\'' + ", dateOfAppointment=" + dateOfAppointment + ", prescription=" + prescription + ", bloodType='" + bloodType + '\'' + ", patientName='" + patientName + '\'' + ", doctorName='" + doctorName + '\'' + ", appointmentID=" + appointmentID + '}';
    }
}

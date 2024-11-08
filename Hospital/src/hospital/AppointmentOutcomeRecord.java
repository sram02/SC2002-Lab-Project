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

    public AppointmentOutcomeRecord(int appointmentID, String diagnosis, String treatment, Date dateOfAppointment,
                                    PrescribedMedication prescription, String bloodType, String patientName, String doctorName) {
        this.appointmentID = appointmentID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dateOfAppointment = dateOfAppointment;
        this.prescription = prescription;
        this.bloodType = bloodType;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }

    public void setPrescriptionStatus(boolean dispensed) {
        this.prescription.setDispensedStatus(dispensed);
    }
}

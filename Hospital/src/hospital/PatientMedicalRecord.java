package hospital;

public class PatientMedicalRecord {
    private String patientName;
    private String dateOfBirth;
    private String diagnosis;
    private String treatment;
    private PrescribedMedication prescribedMedication;

    // Constructor
    public PatientMedicalRecord(String patientName, String dateOfBirth, String diagnosis, String treatment) {
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescribedMedication = null;  // Initially, no medication is prescribed
    }

    // Getter and setter methods
    public String getPatientName() { return patientName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public PrescribedMedication getPrescribedMedication() { return prescribedMedication; }
    public void setPrescribedMedication(PrescribedMedication medication) { this.prescribedMedication = medication; }
}

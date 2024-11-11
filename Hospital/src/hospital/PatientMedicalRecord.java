package hospital;

public class PatientMedicalRecord {
    private String patientId;
    private String diagnosis;
    private String treatment;

    public PatientMedicalRecord(String patientId, String diagnosis, String treatment) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + ", Diagnosis: " + diagnosis + ", Treatment: " + treatment;
    }
}

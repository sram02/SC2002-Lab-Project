package hospital;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<PatientMedicalRecord> medicalRecords;

    public RecordManager() {
        this.medicalRecords = new ArrayList<>();
    }

    public void addRecord(PatientMedicalRecord record) {
        medicalRecords.add(record);
        System.out.println("Medical record added successfully.");
    }

    public void viewRecords() {
        System.out.println("Patient Medical Records:");
        for (PatientMedicalRecord record : medicalRecords) {
            System.out.println(record);
        }
    }

    public void updateRecord(PatientMedicalRecord record) {
        // Logic to update the specific record
        System.out.println("Record updated.");
    }

    // Additional methods to manage records can be added as needed
}

package hospital;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<PatientMedicalRecord> listOfRecords;

    public RecordManager() {
        this.listOfRecords = new ArrayList<>();
    }

    public void addRecord(PatientMedicalRecord record) {
        listOfRecords.add(record);
    }

    public void viewRecords() {
        for (PatientMedicalRecord record : listOfRecords) {
            System.out.println(record);
        }
    }

    public void updateDiagnosis(String diagnosis) {
        // Logic to update diagnosis in patient records
    }

    public void updateTreatment(String treatment) {
        // Logic to update treatment in patient records
    }
}

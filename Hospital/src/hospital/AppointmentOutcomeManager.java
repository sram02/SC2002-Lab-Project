package hospital;

import java.util.List;

public class AppointmentOutcomeManager {
    private List<AppointmentOutcomeRecord> appointmentRecords;

    // Method to view appointment outcome records
    public void viewAppointmentOutcomeRecord() {
        for (AppointmentOutcomeRecord record : appointmentRecords) {
            System.out.println("Date: " + record.getDateOfAppointment());
            System.out.println("Diagnosis: " + record.getDiagnosis());
            System.out.println("Medication: " + record.getPrescription().getName() + 
                               " | Dispensed: " + record.getPrescription().isDispensedStatus());
            System.out.println("-----------------------------");
        }
    }

    // Method to update prescription status
    public void updateDispensedStatus(String appointmentID, boolean dispensedStatus) {
        for (AppointmentOutcomeRecord record : appointmentRecords) {
            if (record.getAppointmentID().equals(appointmentID)) {
                record.getPrescription().setDispensedStatus(dispensedStatus);
                break;
            }
        }
    }
}

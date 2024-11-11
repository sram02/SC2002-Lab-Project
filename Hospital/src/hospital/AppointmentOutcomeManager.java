package hospital;

import java.util.List;

public class AppointmentOutcomeManager {
    private List<AppointmentOutcomeRecord> outcomeRecords;

    public AppointmentOutcomeManager(List<AppointmentOutcomeRecord> outcomeRecords) {
        this.outcomeRecords = outcomeRecords;
    }

    public void viewAppointmentOutcomeRecord() {
        for (AppointmentOutcomeRecord record : outcomeRecords) {
            System.out.println(record);
        }
    }

    public void updateDispensedStatus(AppointmentOutcomeRecord record, String status) {
        record.setStatus(status);
        System.out.println("Updated status for record: " + record);
    }
}

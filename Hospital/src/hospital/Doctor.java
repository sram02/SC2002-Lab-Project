package hospital;

import java.util.List;

public class Doctor extends Staff {
    private AppointmentManager appointmentManager;
    private RecordManager patientRecordManager;

    public Doctor(String userID, String name, String gender, int age) {
        super(userID, name, gender, StaffRole.DOCTOR, age);
        this.appointmentManager = new AppointmentManager();
        this.patientRecordManager = new RecordManager();
    }

    public void viewPatientRecords(Patient patient) {
        if (patient != null) {
            patient.getRecordManager().viewRecords(); // Displays patient records
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void updatePatientRecord(Patient patient, String diagnosis, String treatment) {
        patient.getRecordManager().updateDiagnosis(diagnosis);
        patient.getRecordManager().updateTreatment(treatment);
    }

    public AppointmentManager getAppointmentManager() {
        return appointmentManager;
    }

    // view the doctor schedule
    public void viewSchedule() {
        System.out.println("Doctor's Schedule:");
        // retrieve appointments from AppointmentManager
        List<Appointment> appointments = appointmentManager.getAppointments();
        for (Appointment appointment : appointments) {
            System.out.println("Appointment with Patient ID: " + appointment.getPatientID() +
                               " on " + appointment.getDate() + " at " + appointment.getTime());
        }
    }
}
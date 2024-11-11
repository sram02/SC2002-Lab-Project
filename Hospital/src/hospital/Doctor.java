package hospital;

import java.util.Calendar;

public class Doctor extends Staff {
    private DRecordManager recordManager;
    private DAppointmentManager appointmentManager;

    public Doctor(String userID, String password, String name, String gender, int age) {
        super(userID, password, name, gender, age, StaffRole.DOCTOR); // Pass StaffRole.DOCTOR
        this.recordManager = new DRecordManager();
        this.appointmentManager = new DAppointmentManager();
    }

    public void viewPatientRecords() {
        recordManager.viewPatientRecord();
    }

    public void setAvailability(Calendar calendar) {
        appointmentManager.setAvailability(calendar);
    }
}

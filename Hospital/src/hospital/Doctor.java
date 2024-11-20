package hospital;

public class Doctor extends Staff {
    private final DRecordManager recordManager;
    private final DAppointmentManager appointmentManager;

    public Doctor(String userID, String password, String name, String gender, int age) {
        super(userID, password, name, gender, age, StaffRole.DOCTOR);
        this.recordManager = new DRecordManager();
        this.appointmentManager = new DAppointmentManager();
    }

    public DRecordManager get_DRM() {
        return recordManager;
    }

    public DAppointmentManager get_DAM() {
        return appointmentManager;
    }
}

package hospital;

public class Patient extends User {
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String bloodType;
    private RecordManager recordManager;
    private PAppointmentManager appointmentManager;

    public Patient(String userID, String password, String name, String gender, 
                   String dateOfBirth, String phoneNumber, String email, 
                   String bloodType, RecordManager recordManager, 
                   PAppointmentManager appointmentManager) {
        super(userID, password, name, gender, UserRole.PATIENT);
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
        this.recordManager = recordManager;
        this.appointmentManager = appointmentManager;
    }

    // Patient-specific methods
    public void viewMedicalRecord() {
        recordManager.viewRecords();
    }

    public void scheduleAppointment(Appointment appointment) {
        appointmentManager.schedule(appointment);
    }

    public void viewAppointments() {
        appointmentManager.viewScheduleStatus();
    }

    public void rescheduleAppointment(Appointment oldAppointment, Appointment newAppointment) {
        appointmentManager.reschedule(oldAppointment, newAppointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentManager.cancel(appointment);
    }

    // Getters and setters
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

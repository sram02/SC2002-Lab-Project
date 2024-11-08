package hospital;

public class Patient extends User {
    private String dateOfBirth;
    private String email; // Email as contact information
    private String bloodType;
    private RecordManager recordManager;

    public Patient(String userID, String name, String gender, String dateOfBirth, String email, String bloodType) {
        super(userID, "password", name, gender, UserRole.PATIENT);
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.bloodType = bloodType;
        this.recordManager = new RecordManager();
    }
    
    public void viewScheduledAppointments() {
        if (AppointmentManager != null) {
            AppointmentManager.viewAppointmentsForPatient(this.getUserID());
        } else {
            System.out.println("No appointments found for this patient.");
        }
    }
    
    public void viewMedicalRecord() {
        System.out.println("Patient ID: " + userID);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Blood Type: " + bloodType);
        recordManager.viewRecords();
    }

    public void updateContactInfo(String newEmail, int newPhoneNumber) {
        this.email = newEmail;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Contact information updated.");
    }

    public RecordManager getRecordManager() {
        return recordManager;
    }
}

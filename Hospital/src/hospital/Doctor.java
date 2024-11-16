package hospital;

public class Doctor extends Staff {
	
	// Attributes
    private final DRecordManager recordManager; // Personal patient records
    private final DAppointmentManager appointmentManager; // Personal schedules
    
    // Constructor
    public Doctor(String userID, String password, String name, String gender, int age) {
        super(userID, password, name, gender, age, StaffRole.DOCTOR); // Pass StaffRole.DOCTOR
        this.recordManager = new DRecordManager();
        this.appointmentManager = new DAppointmentManager();
    }

    public DRecordManager getRecordManager() {
    	return recordManager;
    }

    public DAppointmentManager getAppointmentManager() {
    	return appointmentManager;
    }   
    
}

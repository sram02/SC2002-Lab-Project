package hospital;

public class Doctor extends Staff {
	
	//attributes
    private DRecordManager recordManager;
    private DAppointmentManager appointmentManager;
    
    
    //constructor
    public Doctor(String userID, String password, String name, String gender, int age) {
        super(userID, password, name, gender, age, StaffRole.DOCTOR); // Pass StaffRole.DOCTOR
        this.recordManager = new DRecordManager();
        this.appointmentManager = new DAppointmentManager();
    }
    
    public DRecordManager get_RM() {
    	return recordManager;
    }
    
    public DAppointmentManager get_AM() {
    	return appointmentManager;
    }   
}

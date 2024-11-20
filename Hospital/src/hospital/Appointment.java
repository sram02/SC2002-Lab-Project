
package hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Appointment {
	
	public enum AppointmentStatus {
		PENDING, CONFIRMED, CANCELLED, COMPLETED
	}
	
    private String patientID;
    private String doctorID;
    private LocalDate date;
    private String time;
    private AppointmentStatus status;
    private AppointmentOutcomeRecord AppointmentOutcome;

    
    public Appointment(String patientID, String doctorID, LocalDate date, String time) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
        this.status = AppointmentStatus.PENDING;
        this.AppointmentOutcome = null;
    }

    // Getters and Setters
    public String getPatientID() {
        return patientID;
    }
    
    public void setPatientID(String ID) {
    	this.patientID = ID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    
    public AppointmentStatus getStatus() {
    	return this.status;
    }
    
    public void cancel() {
    	this.status = AppointmentStatus.CANCELLED;
    }
    
    public void accept() {
    	this.status = AppointmentStatus.CONFIRMED;
    }
    
    public void complete() {
    	this.status = AppointmentStatus.COMPLETED;
    }
    
    public void revert() {
    	this.status = AppointmentStatus.PENDING;
    }

    @Override
    public String toString() {
    	DateTimeFormatter dateOnlyFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate
        String formattedDate = date.format(dateOnlyFormat);
    	
        System.out.println("Appointment with Doctor ID: " + doctorID + 
        		", Date: " + formattedDate 
        		+ ", Time: " + time);
        System.out.println("Patient ID: " + patientID);
        
        switch (status) {
		case CANCELLED:
			System.out.println("Status: Cancelled");
			break;
		case COMPLETED:
			System.out.println("Status: Completed");
			break;
		case CONFIRMED:
			System.out.println("Status: Confirmed");
			break;
		case PENDING:
			System.out.println("Status: Pending");
			break;
		default:
			break;
        	
        }
        return "\n";
    }
    
    public void create_AOR(String diagnosis, String treatment) {
    	this.AppointmentOutcome = new AppointmentOutcomeRecord(diagnosis, treatment);
    }
    
    public AppointmentOutcomeRecord get_AOR() {
    	return AppointmentOutcome;
    }
}

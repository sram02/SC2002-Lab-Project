
package hospital;

import java.util.Date;

public class Appointment {
	
	public enum AppointmentStatus {
		PENDING, CONFIRMED, CANCELLED, COMPLETED
	}
	
    private String patientID;
    private String doctorID;
    private Date date;
    private String time;
    private AppointmentStatus status;
    private AppointmentOutcomeRecord AppointmentOutcome;

    
    public Appointment(String patientID, String doctorID, Date date, String time) {
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

    public String getDoctorID() {
        return doctorID;
    }

    public Date getDate() {
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

    @Override
    public String toString() {
        System.out.println("Appointment with Doctor ID: " + doctorID + ", Date: " + date + ", Time: " + time);
        System.out.println("Patient ID: " + patientID);
        return "\n";
    }
    
    public void create_AOR(String diagnosis, String treatment) {
    	this.AppointmentOutcome = new AppointmentOutcomeRecord(diagnosis, treatment);
    }
    
    public AppointmentOutcomeRecord get_AOR() {
    	return AppointmentOutcome;
    }
}

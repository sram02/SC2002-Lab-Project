package hospital;

import java.util.Date;

public class Appointment {
    private String patientID;
    private String doctorID;
    private Date date;
    private String time;

    public Appointment(String patientID, String doctorID, Date date, String time) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
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

    @Override
    public String toString() {
        return "Appointment with Doctor ID: " + doctorID + ", Date: " + date + ", Time: " + time;
    }
}

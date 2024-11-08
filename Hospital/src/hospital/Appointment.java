package hospital;

import java.util.Date;

public class Appointment {
    private String patientID;
    private String doctorID;
    private AppointmentStatus status;
    private Date date;
    private String time;

    public Appointment(String patientID, String doctorID, AppointmentStatus status, Date date, String time) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment: Patient ID = " + patientID + ", Doctor ID = " + doctorID + ", Status = " + status +
               ", Date = " + date + ", Time = " + time;
    }
}

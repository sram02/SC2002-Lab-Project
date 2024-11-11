package hospital;

import java.util.Calendar;

public class DAppointmentManager {
    private Calendar calendar;

    // No-argument constructor
    public DAppointmentManager() {
        this.calendar = Calendar.getInstance(); // Initializes with the current date/time
    }

    // Constructor that takes a Calendar instance
    public DAppointmentManager(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setAvailability(Calendar newAvailability) {
        this.calendar = newAvailability;
        System.out.println("Doctor's availability updated.");
    }

    public void acceptAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        System.out.println("Appointment accepted.");
    }

    public void declineAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        System.out.println("Appointment declined.");
    }
}

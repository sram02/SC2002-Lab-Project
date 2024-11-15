package hospital;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private List<Appointment> appointments;

    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    // Method to schedule an appointment
    public void schedule(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment scheduled in AppointmentManager.");
    }

    // Method to view scheduled appointments
    public void viewScheduleStatus() {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

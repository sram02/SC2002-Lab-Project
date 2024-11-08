package hospital;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private List<Appointment> appointments;

    public AppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    public void schedule(Appointment appointment) {
        appointments.add(appointment);
    }

    public void reschedule(Appointment appointment, Appointment newAppointment) {
        appointments.remove(appointment);
        appointments.add(newAppointment);
    }

    public void cancel(Appointment appointment) {
        appointments.remove(appointment);
    }

    public void viewSchedule() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

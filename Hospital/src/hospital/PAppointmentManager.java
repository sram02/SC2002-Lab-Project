package hospital;

import java.util.ArrayList;
import java.util.List;

public class PAppointmentManager {
    private List<Appointment> appointments;

    public PAppointmentManager() {
        this.appointments = new ArrayList<>();
    }

    public void schedule(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public void viewScheduleStatus() {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void reschedule(Appointment oldAppointment, Appointment newAppointment) {
        if (appointments.contains(oldAppointment)) {
            appointments.remove(oldAppointment);
            appointments.add(newAppointment);
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    public void cancel(Appointment appointment) {
        if (appointments.remove(appointment)) {
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }
}

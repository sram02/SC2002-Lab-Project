package hospital;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PAppointmentManager {
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; // for those that the doctor has accepted
    private ArrayList<Appointment> declined; // for those that the doctor has declined
    private ArrayList<Appointment> completed; // for those that the doctor has completed. It should include AppointmentOutcome
    private DAppointmentManager doctorManager; // ref to the doctors manager
    private StaffManager staffManager; // ref to get list of doc
    private Scanner scanner = new Scanner(System.in);

    public PAppointmentManager(DAppointmentManager doctorManager, StaffManager staffManager) {
        this.pending = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.declined = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.doctorManager = doctorManager;
        this.staffManager = staffManager;
    }

    // Schedule an appointment as a request to the doctor
    public void schedule(String patientID) {
        // Access StaffManager to get the list of doctors
        staffManager.viewStaffByRole(StaffRole.DOCTOR);  

        // Let patient choose a doctor by ID
        System.out.print("Enter the ID of the doctor you wish to book an appointment with: ");
        String doctorID = scanner.nextLine();
        Staff selectedDoctor = null;

        // Find doctor by ID
        for (Staff doctor : staffManager.getAllStaff()) {
            if (doctor.getUserID().equals(doctorID)) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        //View the doctors calendar using View_Schedule()
        if (selectedDoctor != null) {
            if (selectedDoctor instanceof Doctor) {
                Doctor doctor = (Doctor) selectedDoctor;
                doctor.get_DAM().View_Schedule();
                
                // Step 4: Select an appointment slot by index (moved inside the if block)
                System.out.print("Select an available slot by number: ");
                int selectedSlot = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (selectedSlot >= 0 && selectedSlot < doctor.get_DAM().get_calendar().size()) {
                    Appointment chosenSlot = doctor.get_DAM().get_calendar().get(selectedSlot);
                    // Add appointment logic here
                    doctor.get_DAM().pending.add(chosenSlot);
                    pending.add(chosenSlot);
                    doctor.get_DAM().get_calendar().remove(selectedSlot);
                    System.out.println("Appointment request sent successfully! Waiting for doctor's approval.");
                } else {
                    System.out.println("Invalid slot number. Please try again.");
                }
                
            } else {
                System.out.println("Selected staff is not a doctor.");
            }
        } else {
            System.out.println("Doctor not found.");
        }
    }


    // View all appointments with their current status
    public void viewAppointments() {
        System.out.println("Your Appointments:");
        
        System.out.println("\nPending Appointments:");
        for (Appointment appointment : pending) {
            System.out.println(appointment + " (Status: PENDING)");
        }
        
        System.out.println("\nAccepted Appointments:");
        for (Appointment appointment : accepted) {
            System.out.println(appointment + " (Status: ACCEPTED)");
        }
        
        System.out.println("\nDeclined Appointments:");
        for (Appointment appointment : declined) {
            System.out.println(appointment + " (Status: DECLINED)");
        }
        
        System.out.println("\nCompleted Appointments:");
        for (Appointment appointment : completed) {
            System.out.println(appointment + " (Status: COMPLETED)");
        }
    }

    // Cancel an appointment
    public void cancel(String patientID, Date date, String time) {
        Appointment appointment = findAppointment(patientID, date, time);
        if (appointment != null && appointments.remove(appointment)) {
            doctorManager.cancel_APT(patientID, date, time); // Notify the doctor
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // Helper method to find an appointment
    private Appointment findAppointment(String patientID, Date date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatientID().equals(patientID) &&                   
                    appointment.getDate().equals(date) &&
                    appointment.getTime().equals(time)) {
                return appointment;
            }
        }
        return null;
    }

	public void reschedule(Appointment oldAppointment, Appointment newAppointment) {
		// TODO Auto-generated method stub
		
	}
    
    
}

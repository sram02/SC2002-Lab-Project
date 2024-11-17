package hospital;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAppointmentManager {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private ArrayList<Appointment> calendar;
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; // for those that the doctor has accepted
    private ArrayList<Appointment> declined; // for those that the doctor has declined, go back to calendar
    private ArrayList<Appointment> completed; // for those that the doctor has completed. It should include AppointmentOutcome

    private Scanner scanner; // Scanner declared as a private class member

    public DAppointmentManager() {
        this.calendar = new ArrayList<>();
        this.pending = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.declined = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.scanner = new Scanner(System.in); // Initialize scanner here
    }

    // For patient's codes
    public ArrayList<Appointment> get_calendar() {
        return this.calendar;
    }

    public boolean cancel_APT(Appointment appointment) {
    	if (pending.remove(appointment)) {
		    declined.add(appointment);
		    System.out.println("Appointment canceled successfully.");        
		    return true; // Successful cancellation}
    	}
    	else {
	        System.out.println("Appointment not found.");
	        return false;  // Appointment not found
        }
    }
    
    public boolean patient_choose(Appointment appointment) {
    	if (calendar.remove(appointment)) {
		    pending.add(appointment);
		    System.out.println("Appointment slot taken successfully... wait for acceptance");        
		    return true; // Successful cancellation}
    	}
    	else {
	        System.out.println("Appointment not found.");
	        return false;  // Appointment not found
        }	   	
    }
    
    public boolean patient_cancel(Appointment appointment) {
    	if (accepted.remove(appointment)) {
		    declined.add(appointment);
		    System.out.println("Appointment canceled successfully.");        
		    return true; // Successful cancellation}
    	}
    	else {
	        System.out.println("Appointment not found.");
	        return false;  // Appointment not found
        }
    }
    
    public boolean patient_reschedule_p1(Appointment appointment) {
    	if (accepted.remove(appointment)) {
    		calendar.add(appointment);
    		return true;
    	}
    	return false;
    }

    // Method to schedule an appointment
    public void schedule(String doctorID) {
        Date date = null;

        // Get valid date input
        while (date == null) {
            System.out.println("Enter date (yyyy-MM-dd): ");
            String Available_date = scanner.nextLine();

            try {
                // Parse the string into a Date object
                date = dateFormat.parse(Available_date);
                System.out.println("Parsed Date: " + date);
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }

        // Get available time
        System.out.println("Enter time (e.g., 9:00 AM, 1:00 PM, 4:00 PM): ");
        String Available_Time = scanner.nextLine();

        // Check for scheduling conflicts
        for (Appointment appt : calendar) {
            if (appt.getDate().equals(date) && appt.getTime().equals(Available_Time)) {
                System.out.println("This slot is already taken. Please choose a different time.");
                return;
            }
        }

        // Create and add the appointment
        Appointment appointment = new Appointment("Empty", doctorID, date, Available_Time);
        calendar.add(appointment);
        System.out.println("Appointment scheduled in your calendar.");
    }

    public void View_Schedule() { // View empty slots available
        int index = 0;

        for (Appointment appointment : calendar) {
            System.out.println(index + ". ");
            System.out.println(appointment.toString());
            index++;
        }
    }

    public void View_Upcoming_Appointments() {
        int index = 0;

        for (Appointment appointment : pending) {
            System.out.println(index + ". " + appointment.toString());
            index++;
        }
    }

    public void Accept_Appointments() {
        String input;
        int index = 0;
        HospitalManagementSystem hms = HospitalManagementSystem.getInstance();

        System.out.println("Enter which appointment to Accept/Decline:");
        index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()

        Appointment removedItem = pending.remove(index);

        // Accept or Decline the appointment
        while (true) {
            System.out.println("Would you like to accept that appointment request? (Y/N)");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                System.out.println("You entered Yes.");
                removedItem.accept();
                accepted.add(removedItem);
                
                //for patient as well
                Patient patient = hms.getPatientById(removedItem.getPatientID());
                patient.get_PAM().doctor_accept(removedItem);        
                break;
                
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("You entered No.");
                removedItem.cancel();
                declined.add(removedItem);
                
                //for patient as well
                Patient patient = hms.getPatientById(removedItem.getPatientID());
                patient.get_PAM().doctor_reject(removedItem);
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }

    public void Fill_Completed_Appointment() {
        displayAcceptedAppointments();
        int index = getCompletedAppointmentIndex();
        Appointment removedItem = removeAcceptedAppointment(index);
        HospitalManagementSystem hms = HospitalManagementSystem.getInstance();

        String diagnosis = getInput("Enter Diagnosis: ");
        String treatment = getInput("Enter treatment: ");
        String medName = getInput("Enter the prescribed medicine name: ");
        int medQuantity = getMedicineQuantity();

        removedItem.create_AOR(diagnosis, treatment);
        removedItem.get_AOR().set_prescription(medName, medQuantity);
        AppointmentOutcomeManager.NDoutcomeRecords.add(removedItem.get_AOR());
        this.completed.add(removedItem);
        
        //add for patient as well
        Patient patient = hms.getPatientById(removedItem.getPatientID());
        patient.get_PAM().remove_from_pending(removedItem);
        patient.get_RM().add_to_Completed(removedItem);
        System.out.println("Successful.Requesting for medication from pharmacy...");
    }

    // Helper methods to refactor Fill_Completed_Appointment
    private void displayAcceptedAppointments() {
        int index = 0;
        for (Appointment appointment : accepted) {
            System.out.println(index + ". " + appointment.toString());
            index++;
        }
    }

    private int getCompletedAppointmentIndex() {
        System.out.println("Enter which appointment have you completed:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()
        return index;
    }

    private Appointment removeAcceptedAppointment(int index) {
        Appointment removedItem = accepted.remove(index);
        removedItem.complete();
        return removedItem;
    }

    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private int getMedicineQuantity() {
        System.out.println("Enter the quantity of medicine: ");
        return scanner.nextInt();
    }

    // Method to close the scanner
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}

package hospital;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DAppointmentManager {
    private ArrayList<Appointment> calendar;
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; // for those that the doctor has accepted
    private ArrayList<Appointment> declined; // for those that the doctor has declined
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
    
    //for patient's edits
    public ArrayList<Appointment> get_calendar(){
    	return this.calendar;
    }
    
    public ArrayList<Appointment> get_pending(){
    	return this.pending;
    }
    
    public void cancel_APT(String PID, Date date, String time) {
    	Appointment removedItem;
    	int index = 0;
    	for (Appointment appointment : accepted) {
            
    		if (appointment.getPatientID() == PID && appointment.getDate() == date && appointment.getTime() == time) {
    			//remove the item from the accepted list.
    			removedItem = accepted.remove(index);
    			break;
    		}
    		index++;
        }
    	
    	//set status to pending
    	removedItem.revert();
    
    	//delete ID.
    	removedItem.setPatientID("Empty");
    	
    	calendar.add(removedItem);
    }
    //end of patient's helper functions

    // Method to schedule an appointment
    public void schedule(String doctorID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

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

        // Now that date is valid, ask for the time
        System.out.println("Enter time (e.g., 9:00 AM, 1:00 PM, 4:00 PM): ");
        String Available_Time = scanner.nextLine();

        // Create the appointment using the parsed date
        Appointment appointment = new Appointment("Empty", doctorID, date, Available_Time);
        calendar.add(appointment);
        System.out.println("Appointment scheduled in your calendar.");
    }
    
    public void View_Schedule() { //view empty slots available
    	int index = 0;
    	
    	for (Appointment appointment: calendar) {
    		System.out.println(index + ". " + appointment.toString());
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

        System.out.println("Enter which appointment to Accept/Decline:");
        index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()

        Appointment removedItem = pending.remove(index);

        while (true) {
            System.out.println("Would you like to accept that appointment request? (Y/N)");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                System.out.println("You entered Yes.");
                removedItem.accept();
                accepted.add(removedItem);
                break;
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("You entered No.");
                removedItem.cancel();
                declined.add(removedItem);
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }

    public void Fill_Completed_Appointment() {
        int index = 0, Med_quant;
        String diagnosis, treatment, Med_Name;

        for (Appointment appointment : accepted) {
            System.out.println(index + ". " + appointment.toString());
            index++;
        }

        System.out.println("Enter which appointment have you completed:");
        index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()

        Appointment removedItem = accepted.remove(index);
        removedItem.complete();

        System.out.println("Enter Diagnosis: ");
        diagnosis = scanner.nextLine();

        System.out.println("Enter treatment: ");
        treatment = scanner.nextLine();

        System.out.println("Enter the prescribed medicine name: ");
        Med_Name = scanner.nextLine();

        System.out.println("Enter the quantity of medicine: ");
        Med_quant = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()

        removedItem.create_AOR(diagnosis, treatment);
        removedItem.get_AOR().set_prescription(Med_Name, Med_quant);

        this.completed.add(removedItem);
    }

    // Method to close the scanner
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}

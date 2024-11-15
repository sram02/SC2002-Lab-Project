package hospital;

import java.util.ArrayList;
import java.util.Scanner;
import hospital.Appointment.AppointmentStatus;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DAppointmentManager {
    private ArrayList<Appointment> calendar;
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; //for those that the doctor has accepted
    private ArrayList<Appointment> declined; //for those that the doctor has declined
    private ArrayList<Appointment> completed; //for those that the doctor has completed. It should include AppointmentOutcome

    Scanner scanner = new Scanner(System.in);

    public DAppointmentManager() {
        this.calendar = new ArrayList<>();
        this.pending = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.declined = new ArrayList<>();
        this.completed = new ArrayList<>();
    }

    // Method to schedule an appointment
    public void schedule(String doctorID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;  // Declare the date variable outside the try block

        while (date == null) {
            System.out.println("Enter date (yyyy-MM-dd): ");
            String Available_date = scanner.nextLine();  // Corrected variable name

            try {
                // Parse the string into a Date object
                date = dateFormat.parse(Available_date);  // Assign value to date
                System.out.println("Parsed Date: " + date);

            } catch (ParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }
        
        // Now that date is valid, ask for the time
        System.out.println("Enter time (e.g., 9:00 AM, 1:00 PM, 4:00 PM): ");
        String Available_Time = scanner.nextLine();  // Use nextLine() to get full input including spaces
        
        // Create the appointment using the parsed date
        Appointment appointment = new Appointment("Empty", doctorID, date, Available_Time);
        calendar.add(appointment);
        System.out.println("Appointment scheduled in your calendar.");
    }
    
    public void View_Upcoming_Appointments() {
    	int index = 0;
    	
    	for (Appointment appointment : accepted) {
    		System.out.println(index + ". ");
    		appointment.toString();
    		index++;
    	}
    }
    
    public void Accept_Appointments() {
    	String input;
    	
    	int index = 0;
    	for (Appointment appointment : pending) {
    		System.out.println(index + ". ");
    		appointment.toString();
    		index++;
    	}
    	
    	System.out.println("Enter which appointment to Accept/Decline:");
    	index = scanner.nextInt();
    	Appointment removedItem = pending.remove(index);
    	
    	while (true) {
    		System.out.println("Would you like to accept that appointment request?");
        	input = scanner.nextLine().trim();
        	
    		if (input.equalsIgnoreCase("Y")) {
                System.out.println("You entered Yes.");
                accepted.add(removedItem);                           
                break;
                
            } else if (input.equalsIgnoreCase("N")) {
                System.out.println("You entered No.");
                declined.add(removedItem);                              
                break;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
    	}
    }
    
    public void Fill_Completed_Appointment() {
    	int index, Med_quant;
    	String diagnosis, treatment, Med_Name;
    	
    	this.View_Upcoming_Appointments();
    	
    	System.out.println("Enter which appointment have you completed:");
    	index = scanner.nextInt();
    	Appointment removedItem = accepted.remove(index);
    	
    	removedItem.setStatus(AppointmentStatus.COMPLETED);
    	
    	System.out.println("Enter Diagnosis: ");
    	diagnosis = scanner.nextLine();
    	
    	System.out.println("Enter treatment: ");
    	treatment = scanner.nextLine();
        
        System.out.println("Enter the prescribed medicine name: ");
        Med_Name = scanner.nextLine();
        
        System.out.println("Enter the quantity of medicine: ");
        Med_quant = scanner.nextInt();
        
        removedItem.create_AOR(diagnosis, treatment);       
        
        removedItem.get_AOR().set_prescription(Med_Name, Med_quant);
        
        this.completed.add(removedItem);
    }
}

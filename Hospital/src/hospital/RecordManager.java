package hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class RecordManager {
    private ArrayList<Appointment> completed;

    public RecordManager() {
        this.completed = new ArrayList<>();
    }
    
    public ArrayList<Appointment> get_Completed(){
    	return this.completed;
    }
    
    public void add_to_Completed(Appointment appointment) {
    	this.completed.add(appointment);
    }
    
    public void view_diagoses_treatments(){
    	for (Appointment appointment : completed) {
    		System.out.println("On " + appointment.getDate() + ": " );
    		System.out.println("diagnosis = " + appointment.get_AOR().get_diagnosis()+  "treatment=" + appointment.get_AOR().get_treatment());
    	}
    }
    
    public void updatePersonalInfo(Patient patient, Scanner scanner) {
    	System.out.println("""
    			Which information would you like to change?
    			1. Name/ alias
    			2. Phone number
    			3. E-mail address   			
    			""");
    	int choice;
    	String input;
    	
    	while (true) {
    		choice = scanner.nextInt();
    	
	    	switch(choice) {
	    	case 1:
	    		System.out.println("Enter new name: ");
	    		input = scanner.nextLine();
	    		patient.setName(input);
	    		return;
	    	case 2:
	    		System.out.println("Enter phone number: ");
	    		input = scanner.nextLine();
	    		patient.setPhoneNumber(input);
	    		return;
	    	case 3: 
	    		System.out.println("Enter new E-mail address: ");
	    		input = scanner.nextLine();
	    		patient.setEmail(input);
	    		return;
	    	default:
	    		System.out.println("Invalid input! Try again!");
	    		break;
	    	}	
    	} 	
    }
    
    public void viewAppointmentOutcomes() {
    	System.out.println("\n Appointment outcomes: ");
    	for (Appointment appointment: completed) {
    		appointment.toString();
    		appointment.get_AOR().toString();
    	}
    }
}

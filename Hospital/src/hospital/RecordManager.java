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
    
    public void view_diagoses_treatments() {
        if (completed.isEmpty()) {
            System.out.println("No prior appointments found. Patient has not been to any appointments yet.");
        } else {
            for (Appointment appointment : completed) {
                System.out.println("On " + appointment.getDate() + ": ");
                System.out.println("diagnosis = " + appointment.get_AOR().get_diagnosis() + " treatment = " + appointment.get_AOR().get_treatment());
            }
        }
    }
    
    public void updatePersonalInfo(Patient patient, Scanner scanner) {
        while (true) {
            System.out.println("""
                Which information would you like to change?
                1. Name/alias
                2. Phone number
                3. E-mail address
                4. Return to previous menu
                """);
            
            // Read the entire line as a String first
            String input = scanner.nextLine().trim();
            
            // Try to parse it as an integer
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            
            switch(choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    if (!newName.isEmpty()) {
                        patient.setName(newName);
                        System.out.println("Name updated successfully!");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter new phone number: ");
                    String newPhone = scanner.nextLine().trim();
                    if (!newPhone.isEmpty()) {
                        patient.setPhoneNumber(newPhone);
                        System.out.println("Phone number updated successfully!");
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter new email address: ");
                    String newEmail = scanner.nextLine().trim();
                    if (!newEmail.isEmpty()) {
                        patient.setEmail(newEmail);
                        System.out.println("Email updated successfully!");
                    }
                    break;
                    
                case 4:
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println(); 
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
package hospital;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class AppointmentOutcomeManager {
    private static AppointmentOutcomeManager instance;

    // Static list to store non-dispensed outcome records
    private static List<AppointmentOutcomeRecord> NDoutcomeRecords = new ArrayList<>();

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Private constructor to prevent external instantiation
    private AppointmentOutcomeManager() {}

    // Singleton getInstance method
    public static AppointmentOutcomeManager getInstance() {
        if (instance == null) {
            instance = new AppointmentOutcomeManager();
        }
        return instance;
    }

    // View all Appointment Outcome Records
    public boolean viewAORs() {
        if (NDoutcomeRecords == null || NDoutcomeRecords.isEmpty()) {
            System.out.println("No records to display.");
            return false;
        }

        int index = 0;
        for (AppointmentOutcomeRecord record : NDoutcomeRecords) {
            System.out.println(index + ". ") ;
            record.toString();
            index++;
        }
        return true;
    }

    // Prescribe medication and dispense
    public void prescribe(InventoryManager PIM) {
        if (!viewAORs()) 
        	return;
        
        int index = -1;
        // Loop to ensure valid index input
        while (index < 0 || index >= NDoutcomeRecords.size()) {
            System.out.println("Enter the record number to dispense for: ");
            if (scanner.hasNextInt()) {
                index = scanner.nextInt();
                if (index < 0 || index >= NDoutcomeRecords.size()) {
                    System.out.println("Invalid index. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume invalid input
            }
        }

        // Remove the selected record
        AppointmentOutcomeRecord record = NDoutcomeRecords.get(index);

        // Remove from inventory
        String medname = record.get_PM().getName();
        int quantity = record.get_PM().getQuantity();
        if (PIM.takeMedicine(medname, quantity)) {
            record.get_PM().dispense();
            NDoutcomeRecords.remove(index);
        }
    }
    
    public void add_AOR(AppointmentOutcomeRecord record) {
    	NDoutcomeRecords.add(record);
    }
}

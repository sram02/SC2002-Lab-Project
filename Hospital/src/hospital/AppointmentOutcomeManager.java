package hospital;

import java.util.List;
import java.util.Scanner;

public class AppointmentOutcomeManager {
    private static AppointmentOutcomeManager instance;

    // Static list to store non-dispensed outcome records
    public static List<AppointmentOutcomeRecord> NDoutcomeRecords;

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
    public void viewAORs() {
        if (NDoutcomeRecords == null || NDoutcomeRecords.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }

        int index = 0;
        for (AppointmentOutcomeRecord record : NDoutcomeRecords) {
            System.out.println(index + ". " + record);
            index++;
        }
    }

    // Prescribe medication and dispense
    public void prescribe(InventoryManager PIM) {
        viewAORs();
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
        AppointmentOutcomeRecord record = NDoutcomeRecords.remove(index);

        // Remove from the inventory
        String medname = record.get_PM().getName();
        int quantity = record.get_PM().getQuantity();
        if (PIM.takeMedicine(medname, quantity)) {
            record.get_PM().Dispense();
        }
    }
}

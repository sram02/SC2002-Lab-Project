
package hospital;

import java.util.Scanner;
import java.util.InputMismatchException;

public class PharmacistMenu {
    private Pharmacist pharmacist;
    private Scanner scanner;
    
    HospitalManagementSystem hms = HospitalManagementSystem.getInstance();

    public PharmacistMenu(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        System.out.println("Welcome, " + pharmacist.getName() + "! This is the Pharmacist Menu.");
        while (true) {
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Prescribe");
            System.out.println("3. View Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Change Password");
            System.out.println("6. Logout \n");
            System.out.print("Enter your choice: ");
            int choice = getChoice(); 

            switch (choice) {
                case 1:
                    System.out.println("Viewing Appointment Outcome Records to be fulfilled...");
                    pharmacist.getAOM().viewAORs();
                    break;
                case 2:
                    // Update Prescription Status logic
                	System.out.println("Updating prescription status...");
                	pharmacist.getAOM().prescribe(pharmacist.getInventoryManager());
                    break;
                case 3:
                    pharmacist.viewInventory();
                    break;
                case 4:
                    System.out.print("Enter medicine name: ");
                    String medicineName = scanner.nextLine();

                    // Check if medicine exist in inventory
                    if (pharmacist.getInventoryManager().getInventory().getMedicineByName(medicineName) == null) {
                        System.out.println("Invalid medicine. Please try again.\n");
                        break;
                    }

                    System.out.print("Enter requested quantity: ");
                    int quantity;
                    try {
                        quantity = scanner.nextInt();
                        scanner.nextLine();  
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid quantity. Please enter a valid number.");
                        scanner.nextLine(); 
                        break;
                    }
                    pharmacist.submitReplenishmentRequest(medicineName, quantity, hms.getAdminInventoryManager());
                    break;
                case 5:
                    pharmacist.changePassword(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private int getChoice() {
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
        return choice;
    }
}

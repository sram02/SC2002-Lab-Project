package hospital;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        HospitalManagementSystem hms = HospitalManagementSystem.getInstance(); // Use singleton
        hms.loadData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hospital Management System");

        while (true) {
            LoginPage loginPage = new LoginPage(hms);
            User user = loginPage.start();

            if (user != null) {
                if (user instanceof Administrator) {
                    AdministratorMenu adminMenu = new AdministratorMenu(
                        (Administrator) user
                    );
                    adminMenu.display();
                } else if (user instanceof Pharmacist) {
                    InventoryManager inventoryManager = new InventoryManager(hms.getInventory());
                    PharmacistMenu pharmacistMenu = new PharmacistMenu(
                        (Pharmacist) user
                    );
                    pharmacistMenu.display();
                } else if (user instanceof Doctor) {
                	DoctorMenu doctorMenu = new DoctorMenu((Doctor) user, scanner); 
                    doctorMenu.display();
                } else if (user instanceof Patient) {
                    PatientMenu patientMenu = new PatientMenu((Patient) user, scanner);
                    patientMenu.display();
                }
            }

            System.out.print("Would you like to login again? (yes/no): ");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Exiting the Hospital Management System. Goodbye!");
        scanner.close();
    }
}

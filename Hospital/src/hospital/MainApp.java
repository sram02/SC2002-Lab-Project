package hospital;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        hms.loadData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hospital Management System");

        while (true) {
            // Using LoginPage to handle user login and display messages
            LoginPage loginPage = new LoginPage(hms);
            User user = loginPage.start();

            if (user != null) {
                // Handle different user roles
                if (user instanceof Administrator) {
                    AdministratorMenu adminMenu = new AdministratorMenu(
                        (Administrator) user, hms.getStaffManager(), hms.getAdminInventoryManager(), hms
                    );
                    adminMenu.display();
                } else if (user instanceof Pharmacist) {
                    InventoryManager inventoryManager = new InventoryManager(hms.getInventory());
                    PharmacistMenu pharmacistMenu = new PharmacistMenu(
                        (Pharmacist) user, inventoryManager, hms.getAdminInventoryManager()
                    );
                    pharmacistMenu.display();
                } else if (user instanceof Doctor) {
                    DoctorMenu doctorMenu = new DoctorMenu((Doctor) user);
                    doctorMenu.display();
                } else if (user instanceof Patient) {
                    PatientMenu patientMenu = new PatientMenu((Patient) user);
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

package hospital;

import java.io.FileNotFoundException;

public class MainApp {
    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();

        try {
            hms.loadPatients();
            hms.loadStaff();
            hms.loadMedicines();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading data files: " + e.getMessage());
            return;
        }

        LoginPage loginPage = new LoginPage(hms);
        boolean exit = false;

        while (!exit) {
            User user = loginPage.start();

            if (user == null) {
                System.out.println("Exiting the Hospital Management System.");
                exit = true;
            } else {
                // Call the appropriate menu based on the user role
                if (user instanceof Patient) {
                    new PatientMenu((Patient) user, hms).display();
                } else if (user instanceof Doctor) {
                    new DoctorMenu((Doctor) user, hms).display();
                } else if (user instanceof Pharmacist) {
                    new PharmacistMenu((Pharmacist) user, hms).display();
                } else if (user instanceof Administrator) {
                    new AdministratorMenu((Administrator) user, hms).display();
                }
            }

            // After logout, prompt if the user wants to log in again
            System.out.println("Would you like to log in again? (yes/no)");
            String response = new java.util.Scanner(System.in).nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                exit = true;
                System.out.println("Thank you for using the Hospital Management System. Goodbye!");
            }
        }
    }
}

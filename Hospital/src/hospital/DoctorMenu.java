package hospital;

import java.util.Scanner;

public class DoctorMenu {
    private Doctor doctor;
    private Scanner scanner;

    public DoctorMenu(Doctor doctor, Scanner scanner) {
        this.doctor = doctor;
        this.scanner = scanner;
    }

    public void display() {
        while (true) {
            showMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    viewPatientRecords();
                    break;
                case 3:
                    updatePatientRecords();
                    break;
                case 4:
                    viewSchedule();
                    break;
                case 5:
                    setAvailability();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    acceptDeclineAppointments();
                    break;
                case 8:
                    fillCompletedAppointments();
                    break;
                case 9:
                    if (confirmLogout()) {
                        System.out.println("Logging out...");
                        return; // Exit the menu and go back to the main login screen
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add space for readability
        }
    }

    private void showMenu() {
        System.out.println("Welcome, Dr. " + doctor.getName() + "! This is the Doctor Menu.");
        System.out.println("1. Change password");
        System.out.println("2. View Patient Medical Records under my care");
        System.out.println("3. Update Patient Medical Records under my care");
        System.out.println("4. View my personal schedule");
        System.out.println("5. Set Availability for new appointments");
        System.out.println("6. View list of upcoming appointments");
        System.out.println("7. Accept/Decline appointments");
        System.out.println("8. Fill up completed appointments");
        System.out.println("9. Logout");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        int choice = -1;
        while (choice < 1 || choice > 9) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        }
        return choice;
    }

    private void changePassword() {
        System.out.println("Changing password...");
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        this.doctor.setPassword(password);
        System.out.println("Password changed successfully.");
    }

    private void viewPatientRecords() {
        System.out.println("Viewing patient records...");
        this.doctor.get_DRM().viewPatientMedicalRecord(scanner);
    }

    private void updatePatientRecords() {
        System.out.println("Updating patient records...");
        this.doctor.get_DRM().UpdatePatientRecord(scanner);
    }

    private void viewSchedule() {
        System.out.println("Viewing availability...");
        this.doctor.get_DAM().View_Schedule();
    }

    private void setAvailability() {
        System.out.println("Setting availability...");
        this.doctor.get_DAM().schedule(this.doctor.getUserID());
    }

    private void viewUpcomingAppointments() {
        System.out.println("Viewing list of upcoming appointments...");
        this.doctor.get_DAM().View_Upcoming_Appointments();
    }

    private void acceptDeclineAppointments() {
        System.out.println("Accepting/Declining appointments...");
        this.doctor.get_DAM().Accept_Appointments();
    }

    private void fillCompletedAppointments() {
        System.out.println("Filling up completed appointments...");
        this.doctor.get_DAM().Fill_Completed_Appointment();
    }

    private boolean confirmLogout() {
        System.out.print("Are you sure you want to log out? (Y/N): ");
        String input = scanner.nextLine().trim();
        return input.equalsIgnoreCase("Y");
    }
}

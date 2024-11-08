package hospital;

import java.util.Scanner;

public class AdministratorMenu {
    private Administrator admin;
    private HospitalManagementSystem hms;

    public AdministratorMenu(Administrator admin, HospitalManagementSystem hms) {
        this.admin = admin;
        this.hms = hms;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Inventory");
            System.out.println("3. Approve Replenishment Requests");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manageStaff();
                    break;
                case 2:
                    admin.viewInventory(); // Calls the viewInventory method in Administrator
                    break;
                case 3:
                    System.out.print("Enter medicine name to approve replenishment: ");
                    String medicineName = scanner.nextLine();
                    admin.approveReplenishmentRequest(medicineName);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void manageStaff() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("\nManage Hospital Staff:");
        System.out.println("1. Add Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter staff name: ");
                String name = scanner.nextLine();
                System.out.print("Enter staff gender: ");
                String gender = scanner.nextLine();
                System.out.print("Enter staff age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter staff role (DOCTOR, PHARMACIST, ADMINISTRATOR): ");
                String roleInput = scanner.nextLine();
                StaffRole role = StaffRole.valueOf(roleInput.toUpperCase());
                Staff newStaff = new Staff("generatedID", name, gender, role, age); // Assuming generatedID for simplicity
                admin.addStaffMember(newStaff);
                System.out.println("Staff member added successfully.");
                break;
            case 2:
                System.out.print("Enter staff ID to update: ");
                String staffID = scanner.nextLine();
                // Assume hms has a getStaffByID method to retrieve a staff member
                Staff staffToUpdate = hms.getStaffByID(staffID);
                if (staffToUpdate != null) {
                    System.out.print("Enter new name: ");
                    staffToUpdate.setName(scanner.nextLine());
                    System.out.print("Enter new gender: ");
                    staffToUpdate.setGender(scanner.nextLine());
                    System.out.print("Enter new age: ");
                    staffToUpdate.setAge(scanner.nextInt());
                    System.out.print("Enter new role (DOCTOR, PHARMACIST, ADMINISTRATOR): ");
                    scanner.nextLine(); // consume newline
                    staffToUpdate.setRole(StaffRole.valueOf(scanner.nextLine().toUpperCase()));
                    System.out.println("Staff member updated successfully.");
                } else {
                    System.out.println("Staff member not found.");
                }
                break;
            case 3:
                System.out.print("Enter staff ID to remove: ");
                String removeStaffID = scanner.nextLine();
                admin.removeStaffMember(removeStaffID);
                System.out.println("Staff member removed successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

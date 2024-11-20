package hospital;

import java.util.Scanner;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
// import java.util.Iterator;
import java.util.InputMismatchException;

public class AdministratorMenu {
    private Administrator administrator;
    private AdminInventoryManager adminInventoryManager;
    
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    HospitalManagementSystem hms = HospitalManagementSystem.getInstance();
    StaffManager  staffManager = hms.getStaffManager();
    Scanner scanner = new Scanner(System.in);

    public AdministratorMenu(Administrator administrator ) {
    	    this.administrator = administrator;
    	    this.adminInventoryManager = HospitalManagementSystem.getInstance().getAdminInventoryManager();
    }

    public void display() {
        System.out.println("Welcome, " + administrator.getName() + "! This is the Administrator Menu.");
        while (true) {
        	System.out.println("1. Register patient");
            System.out.println("2. Manage Staff");
            System.out.println("3. View Appointments");
            System.out.println("4. Manage Inventory");
            System.out.println("5. Approve Replenishment Requests");
            System.out.println("6. Change Password");
            System.out.println("7. Logout \n");
            System.out.print("Enter your choice: ");
            int choice = getChoice();
            switch (choice) {
            	case 1:
            		addPatient();
            		break;
                case 2:
                    manageStaff();
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                	manageInventory();
                    break;
                case 5:
                    viewAndApproveRequests(); 
                    break;
                case 6:
                    administrator.changePassword(scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private int getChoice() {
        int choice = -1;
        while (choice < 1 || choice > 7) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }
        return choice;
    }
    
    private void addPatient() {
        System.out.println("Adding New Patient:");

        // User ID
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        // Check if User ID already exists
        if (hms.getPatientById(userID) != null || staffManager.getStaffById(userID) != null) {
            System.out.println("A user with this User ID already exists. Please try again.\n");
            return;
        }

        // Name
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name. Name must contain only alphabets and should not be empty. Please try again.\n");
            return;
        }

        // Date of Birth
        String dob;
        while (true) {
            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            dob = scanner.nextLine();
            try {
                LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter in yyyy-MM-dd format.");
            }
        }

        // Gender
        String gender;
        while (true) {
            System.out.print("Enter Gender (Male/Female): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                break;
            }
            System.out.println("Invalid gender. Please enter 'Male' or 'Female'.");
        }

        // Email
        String email;
        while (true) {
            System.out.print("Enter Email Address: ");
            email = scanner.nextLine();
            if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                break;
            }
            System.out.println("Invalid email format. Please enter a valid email address (e.g., name@example.com).");
        }

        // Phone Number
        String phoneNumber;
        while (true) {
            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("^[0-9]{10,15}$")) {
                break;
            }
            System.out.println("Invalid phone number. Please enter a valid number with 10-15 digits.");
        }

        // Blood Type
        String bloodType;
        while (true) {
            System.out.print("Enter Blood Type (e.g., A+, B-, O+, AB-): ");
            bloodType = scanner.nextLine();
            if (bloodType.matches("^(A|B|AB|O)[+-]$")) {
                break;
            }
            System.out.println("Invalid blood type. Please enter a valid blood type (e.g., A+, B-, O+, AB-).");
        }

        // Create Patient
        Patient patient = new Patient(userID, "defaultPassword", name, gender, dob, phoneNumber, email, bloodType);
        hms.add_Patient(patient);
        System.out.println("Patient added successfully.\n");
    }


    private void manageStaff() {
        while (true) {
            System.out.println("Manage Staff:");
            System.out.println("1. View Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Return to Main Menu \n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewStaff();
                    break;
                case 2:
                    addStaff();
                    break;
                case 3:
                    updateStaff();
                    break;
                case 4:
                    removeStaff();
                    break;
                case 5:
                	System.out.println("Returning to Main Menu..\n");
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void viewAppointments() {
    	//see the list of doctors
    	staffManager.viewStaffByRole(StaffRole.DOCTOR);
    	
    	//get doctor by index
    	String input;
    	System.out.println("Enter Doctor by ID, to view his appointments: ");
    	input = scanner.nextLine();
    	Staff doc = staffManager.getStaffById(input);
    	//choose to view his appointments
    	
    	Doctor doctor;
    	if (doc instanceof Doctor) {
    		doctor = (Doctor) doc;
    	}
    	else {
    		System.out.println("Sorry, staff selected is not a doctor. Try again.");
    		return;
    	}
    	
    	//only view accepted, cancelled, completed.
    	System.out.println("""
    			Which appointments would you like to view?
    			1. Confirmed appointments
    			2. Cancelled appointments
    			3. Completed appointments
    			
    			input:
    			""");
    	
    	int choice = scanner.nextInt();
    	
    	switch(choice) {
    	case 1:
    		System.out.println("Confirmed appointments: ");
    		doctor.get_DAM().View_Upcoming_Appointments();
    		break;
    	case 2:
    		System.out.println("Cancelled appointments: ");
    		doctor.get_DAM().View_cancelled();
    		break;
    	case 3:
    		System.out.println("Completed appointments: ");
    		doctor.get_DAM().view_completed();
    		break;
    	default:
    		System.out.println("Invalid choice.");
    		break;
    	}
    }

    private void viewStaff() {
        System.out.println("Viewing Staff:");
        staffManager.viewStaff();
        System.out.println("\nReturning to the Manage Staff menu...\n");
    }

    private void addStaff() {
        System.out.println("Adding New Staff:");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        // check if userID exists
        if (staffManager.getStaffById(userID) != null) {
            System.out.println("A staff member with this User ID already exists. Please try again.\n");
            return;
        }
        
        for (Patient patient : hms.getPatients()) {
            if (patient.getUserID().equals(userID)) {
                System.out.println("A patient with this User ID already exists. Please try again.\n");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        // check name
        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid name. Name must contain only alphabets and should not be empty. Please try again.\n");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine();
        
        // check gender
        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            System.out.println("Invalid gender. Please enter 'Male' or 'Female'.\n");
            return;
        }

        System.out.print("Enter Age: ");
        int age;
        
        try {
            age = scanner.nextInt();
            if (age <= 0) {
                System.out.println("Invalid age. Age must be a positive number. Please try again.\n");
                scanner.nextLine(); 
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for age. Please enter a valid number.\n");
            scanner.nextLine(); 
            return;
        }

        scanner.nextLine(); 

        System.out.print("Enter Role (DOCTOR, PHARMACIST, ADMINISTRATOR): ");
        String roleInput = scanner.nextLine().toUpperCase();
        StaffRole role;
        try {
            role = StaffRole.valueOf(roleInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role entered. Please enter a valid role (DOCTOR, PHARMACIST, ADMINISTRATOR).\n");
            return;
        }

        // Create staff based on role
        Staff newStaff;
        switch (role) {
            case DOCTOR:
                newStaff = new Doctor(userID, password, name, gender, age);
                hms.addDoctor((Doctor) newStaff);
                break;
            case PHARMACIST:
                InventoryManager inventoryManager = new InventoryManager(hms.getInventory());
                newStaff = new Pharmacist(userID, password, name, gender, age, inventoryManager);
                hms.addPharmacist((Pharmacist) newStaff);
                break;
            case ADMINISTRATOR:
                AdminInventoryManager adminInventoryManager = hms.getAdminInventoryManager();
                newStaff = new Administrator(userID, password, name, gender, age, adminInventoryManager);
                hms.addAdministrator((Administrator) newStaff);
                break;
            default:
                System.out.println("Invalid role. Staff not added.");
                return;
        }
        System.out.println("Staff added successfully.\n");
    }

    private void updateStaff() {
        System.out.print("Enter User ID of staff to update: ");
        String userID = scanner.nextLine();
        Staff staff = staffManager.getStaffById(userID);

        if (staff == null) {
            System.out.println("Staff not found.\n");
            return;
        }

        System.out.print("Enter New Name (Leave blank to keep current name): ");
        String name = scanner.nextLine();

        // check name if inputed
        if (!name.isEmpty() && (!name.matches("[a-zA-Z ]+") || name.trim().isEmpty())) {
            System.out.println("Invalid name. Name must contain only alphabets and should not be empty. Please try again.\n");
            return;
        }

        System.out.print("Enter New Password (Leave blank to keep current password): ");
        String password = scanner.nextLine();

        System.out.print("Enter Gender (Leave blank to keep current gender): ");
        String gender = scanner.nextLine();

        // check gender if inputed
        if (!gender.isEmpty() && !gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            System.out.println("Invalid gender. Please enter 'Male' or 'Female'.\n");
            return;
        }

        System.out.print("Enter New Age (Leave blank to keep current age): ");
        String ageInput = scanner.nextLine();
        Integer age = null;

        // check age if inputed
        if (!ageInput.isEmpty()) {
            try {
                age = Integer.parseInt(ageInput);
                if (age <= 0) {
                    System.out.println("Invalid age. Age must be a positive number. Please try again.\n");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age. Please enter a valid number.\n");
                return;
            }
        }

        // update staff details in staff manager
        staffManager.updateStaff(userID, name.isEmpty() ? staff.getName() : name,
                                 password.isEmpty() ? staff.getPassword() : password,
                                 gender.isEmpty() ? staff.getGender() : gender);

        if (age != null) {
            staff.setAge(age); 
        }

        System.out.println("Staff updated successfully.\n");
    }


    private void removeStaff() {
        System.out.print("Enter User ID of staff to remove: ");
        String userID = scanner.nextLine();
        if (staffManager.removeStaff(userID)) {
            System.out.println("Staff removed successfully.\n");
        } else {
            System.out.println("Staff not found.\n");
        }
    }
    
    private void manageInventory() {
        while (true) {
            System.out.println("Inventory Management:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Medicine");
            System.out.println("3. Update Medicine Stock");
            System.out.println("4. Update Low Stock Threshold");
            System.out.println("5. Remove Medicine");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminInventoryManager.viewInventory();
                    break;
                case 2:
                    System.out.print("Enter medicine name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter low stock threshold: ");
                    int threshold = scanner.nextInt();
                    scanner.nextLine(); 
                    adminInventoryManager.addMedicine(name, stock, threshold);
                    break;
                case 3:
                	 System.out.print("Enter medicine name: ");
                     String medName = scanner.nextLine();
                     
                     if (adminInventoryManager.getInventory().getMedicineByName(medName) == null) {
                         System.out.println("Invalid medicine. Please try again.\n");
                         break;
                     }
                     
                     System.out.print("Enter new stock level: ");
                     try {
                         int newStock = scanner.nextInt();
                         scanner.nextLine(); 
                         adminInventoryManager.updateStockLevel(medName, newStock);
                     } catch (InputMismatchException e) {
                         System.out.println("Invalid input. Please enter a valid number for stock level.\n");
                         scanner.nextLine(); 
                     }
                     break;
                case 4:
                	System.out.print("Enter medicine name: ");
                    String thresholdName = scanner.nextLine();
                    
                    if (adminInventoryManager.getInventory().getMedicineByName(thresholdName) == null) {
                        System.out.println("Invalid medicine. Please try again.\n");
                        break;
                    }
                    
                    System.out.print("Enter new low stock threshold: ");
                    try {
                        int newThreshold = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        adminInventoryManager.updateLowStockThreshold(thresholdName, newThreshold);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the low stock threshold.\n");
                        scanner.nextLine(); 
                    }
                    break;
                case 5:
                    System.out.print("Enter medicine name to remove: ");
                    String removeName = scanner.nextLine();
                    adminInventoryManager.removeMedicine(removeName);
                    break;
                case 6:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public void viewAndApproveRequests() {
        System.out.println("Pending Replenishment Requests:");

        // Temp list to hold requests to be removed after iteration
        List<ReplenishmentRequest> approvedRequests = new ArrayList<>();
        
        if (adminInventoryManager.getPendingRequests().isEmpty()) {
        	System.out.println("No pending requests available. Quitting...");
        	return;
        }

        for (ReplenishmentRequest request : adminInventoryManager.getPendingRequests()) {
            System.out.println("Medicine: " + request.getMedicineName() + ", Quantity: " + request.getRequestedQuantity());
            System.out.print("Approve this request? (yes/no): ");
            String approval = scanner.nextLine();

            if (approval.equalsIgnoreCase("yes")) {
                adminInventoryManager.approveReplenishmentRequest(request);  // update stock
                approvedRequests.add(request);  
                System.out.println("Request approved for " + request.getMedicineName());
            }
        }
        
        adminInventoryManager.getPendingRequests().removeAll(approvedRequests);

        if (adminInventoryManager.getPendingRequests().isEmpty()) {
            System.out.println("No pending replenishment requests.");
        }
    }

}
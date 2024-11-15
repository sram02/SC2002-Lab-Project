
package hospital;

import java.util.Scanner;



public class DoctorMenu {
    private Doctor doctor;
    
    

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
    }

    public void display() {
    	
    	Scanner scanner = new Scanner(System.in);
       
        while (true) {
            System.out.println("Welcome, Dr. " + doctor.getName() + "! This is the Doctor Menu.");
            System.out.println("0. Change password"); //ram's edit
            System.out.println("1. View Patient Medical Records under my care");
            System.out.println("2. Update Patient Medical Records under my care");
            System.out.println("3. View my personal schedule");
            System.out.println("4. Set Availability for new appoinments");
            System.out.println("5. View list of upcoming appointments");
            System.out.println("6. Accept/Decline appointments");
            System.out.println("7. Fill up completed appointment");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            switch (choice) {
            	case 0:
            		System.out.println("Changing password...");
            		System.out.println("Enter new password: ");
            		String password = scanner.nextLine();
            		this.doctor.setPassword(password);
            		break;
                case 1:
                    // View blood type, past diagnoses, treatments.
                    System.out.println("Viewing patient records...");
                    this.doctor.get_DRM().viewPatientMedicalRecord();
                    break;
                case 2:
                    //updates the patient's past diagnoses and treatments.
                    System.out.println("Updating patient records...");
                    this.doctor.get_DRM().UpdatePatientRecord();
                    break;
                    
                case 3: 
                	System.out.println("Viewing Availability...");
                	this.doctor.get_DAM().View_Schedule();
                	break;
                case 4:
                    // Call method to set availability
                    System.out.println("Setting availability...");
                    this.doctor.get_DAM().schedule(this.doctor.getUserID());
                    break;
                case 5:
                	System.out.println("View list of upcoming appointments...");
                	this.doctor.get_DAM().View_Upcoming_Appointments();
                	break;
                case 6:
                	System.out.println("Accept/decline appointments...");
                	this.doctor.get_DAM().Accept_Appointments();
                	break;
                case 7:
                	System.out.println("Fill up completed appoinments...");
                	this.doctor.get_DAM().Fill_Completed_Appointment();
                	break;
                case 8:
                    System.out.println("Logging out...");
                    scanner.close();
                    return;  // Exit the menu and go back to the main login screen
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add space for readability
        }
    }
}

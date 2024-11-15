package hospital;

import java.util.Scanner;

public class Doctor extends Staff {
	
	//attributes
    private DRecordManager recordManager;
    private DAppointmentManager appointmentManager;
    
    
    //constructor
    public Doctor(String userID, String password, String name, String gender, int age) {
        super(userID, password, name, gender, age, StaffRole.DOCTOR); // Pass StaffRole.DOCTOR
        this.recordManager = new DRecordManager();
        this.appointmentManager = new DAppointmentManager();
    }
    
    public void changePassword(Scanner scanner) {
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if (!this.getPassword().equals(currentPassword)) {
            System.out.println("Incorrect current password.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            this.setPassword(newPassword);
            System.out.println("Password changed successfully. \n");
        } else {
            System.out.println("Passwords do not match. Password change failed. \n");
        }
    }
    
    public DRecordManager get_DRM() {
    	return recordManager;
    }
    
    public DAppointmentManager get_DAM() {
    	return appointmentManager;
    }   
}

package hospital;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Patient extends User {
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String bloodType;
    private RecordManager recordManager;
    private PAppointmentManager appointmentManager;

    public Patient(String userID, String password, String name, String gender,
                   String dateOfBirth, String phoneNumber, String email,
                   String bloodType) {
        super(userID, password, name, gender, UserRole.PATIENT);
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
        this.recordManager = new RecordManager();
        this.appointmentManager = new PAppointmentManager();
    }

    // Patient-specific methods
    @Override
    public String toString() {
        return "Patient Information:\n" +
        		"ID: " + this.getUserID() + "\n" +
                "Name: " + getName() + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Gender: " + this.getGender() + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Email: " + email + "\n" +
                "Blood Type: " + bloodType + "\n";
    }

    // Getters and setters remain the same
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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
    
    public PAppointmentManager get_PAM() {
        return this.appointmentManager;
    }

    public RecordManager get_RM() {
        return this.recordManager;
    }
}
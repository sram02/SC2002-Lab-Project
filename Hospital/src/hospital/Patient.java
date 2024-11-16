package hospital;

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
                   String bloodType, RecordManager recordManager, 
                   PAppointmentManager appointmentManager) {
        super(userID, password, name, gender, UserRole.PATIENT);
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
        this.recordManager = recordManager;
        this.appointmentManager = appointmentManager;
    }

    // Patient-specific methods
    public void viewMedicalRecord() {
        recordManager.viewRecords();
    }

    public void scheduleAppointment(String doctorID) {
        appointmentManager.schedule(this.getUserID());
    }

    public void viewAppointments() {
        appointmentManager.viewAppointments();
    }

    public void rescheduleAppointment(Appointment oldAppointment, Appointment newAppointment) {
        appointmentManager.reschedule(oldAppointment, newAppointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointmentManager.cancel(
        		this.getUserID(),appointment.getDate(),appointment.getTime());
    }

    // Getters and setters
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}

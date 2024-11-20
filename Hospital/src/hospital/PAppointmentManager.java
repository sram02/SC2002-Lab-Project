package hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class PAppointmentManager {
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; // for those that the doctor has accepted
    private ArrayList<Appointment> declined; // for those that the doctor has declined
    private Scanner scanner = new Scanner(System.in);

    public PAppointmentManager() {
        this.pending = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.declined = new ArrayList<>();
    }

    // Schedule an appointment as a request to the doctor
    
    public void schedule(Patient patient) {
        // Viewing all doctors
        StaffManager SM = HospitalManagementSystem.getStaffManager();
        int selectedSlot;
        Doctor doctor;

        SM.viewStaffByRole(StaffRole.DOCTOR);
        
        // Let patient choose a doctor by ID
        System.out.print("Enter the ID of the doctor you wish to book an appointment with: ");
        String doctorID = scanner.nextLine();
        Staff selectedDoctor = null;

        // Find doctor by ID
        selectedDoctor = SM.getStaffById(doctorID);

        if (selectedDoctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        if (selectedDoctor instanceof Doctor) {
            doctor = (Doctor) selectedDoctor;
            
            // Check if calendar is empty
            if (doctor.get_DAM().get_calendar().isEmpty()) {
                System.out.println("No available appointments for this doctor. Please select another doctor.");
                return;
            }

            doctor.get_DAM().View_Schedule();

            // Prompt user to select an appointment slot
            System.out.print("Select an available slot by number (or enter -1 to go back): ");
            selectedSlot = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Allow option to go back
            if (selectedSlot == -1) {

                return;
            }
        } else {
            System.out.println("Selected staff is not a doctor.");
            return;
        }

        if (selectedSlot >= 0 && selectedSlot < doctor.get_DAM().get_calendar().size()) {
            Appointment chosenSlot = doctor.get_DAM().get_calendar().get(selectedSlot);
            chosenSlot.setPatientID(patient.getUserID());

            this.pending.add(chosenSlot);
            doctor.get_DAM().patient_choose(chosenSlot);
            doctor.get_DRM().addPatient(patient);
            System.out.println("Appointment request sent successfully! Waiting for doctor's approval.");
            return;
        }
    }
    
    public void view_appointment(ArrayList<Appointment> list) {
      if (list.isEmpty()) {
        System.out.println("None found... exiting.");
        return;
      }
      
      int index = 0;
      for (Appointment appointment: list) {
        System.out.print(index + ".");
        appointment.toString();
        index++;
      }
    }


    // View all appointments with their current status
    public void viewAppointments(RecordManager RM) {
      
        System.out.println("""
            Choose the following to view:
            1. Pending appointments
            2. Confirmed appointments
            3. Cancelled appointments
            4. Completed appointments
            """);
        
        int choice = scanner.nextInt();
        
        switch(choice) {
        case 1:
          System.out.println("\nPending Appointments:");
          this.view_appointment(pending);
          break;
          
        case 2:
          System.out.println("\n Confirmed Appointments:");
          this.view_appointment(accepted);
          break;
        
      case 3:
        System.out.println("\nCancelled Appointments:");
        this.view_appointment(declined);
        break;
        
      case 4:
          System.out.println("\nCompleted Appointments:");
          this.view_appointment(RM.get_Completed());
          break;
        }

    }


    // Cancel an appointment
    public void cancel() {
      StaffManager SM = HospitalManagementSystem.getStaffManager();
      
      // Display all accepted appointment
        if (accepted.isEmpty()) {
            System.out.println("You have no appointments to cancel.");
            return;
        }
        else {
          System.out.println("\nYour Current Appointments:");
          this.view_appointment(accepted);
        }
        
        // Get user selection
        System.out.print("\nSelect the appointment to cancel (enter number): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Staff doctor;
    // Validate selection
        if (selection >= 0 && selection < accepted.size()) {
            // Get the selected appointment
            Appointment appointmentToCancel = accepted.remove(selection);
            appointmentToCancel.cancel();
            declined.add(appointmentToCancel);
            //shift it from accepted to cancelled
        
            doctor = SM.getStaffById(appointmentToCancel.getDoctorID());
            
            if (doctor instanceof Doctor) {
                Doctor selectedDoctor = (Doctor) doctor;
                selectedDoctor.get_DAM().patient_cancel(appointmentToCancel);
            }
            else{
                System.out.println("Doctor not found.");
                return;
            }
        }
        else {
          System.out.println("Invalid selection. Please try again.");
        } 
    }

  public void reschedule(Patient patient) {
    StaffManager SM = HospitalManagementSystem.getStaffManager();
    // Display all accepted appointments
        
        if (accepted.isEmpty()) {
            System.out.println("You have no appointments to reschedule.");
            return;
        }
        else {
          System.out.println("\nYour Current Appointments:");
          this.view_appointment(accepted);
        }
        
        // Get user selection
        System.out.print("\nSelect the appointment to reschedule (enter number): ");
        int selection = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Staff doctor;
    // Validate selection
        if (selection >= 0 && selection < accepted.size()) {
            // Get the selected appointment
            Appointment appointment = accepted.remove(selection);
            appointment.revert();
            appointment.setPatientID("Empty");
            //shift it from accepted to cancelled
            doctor = SM.getStaffById(appointment.getDoctorID());
            if (doctor instanceof Doctor) {
                Doctor selectedDoctor = (Doctor) doctor;
                selectedDoctor.get_DAM().patient_reschedule_p1(appointment);
                this.schedule(patient);
            }
            else{
                System.out.println("Doctor not found.");
                return;
            }
        }
        else {
          System.out.println("Invalid selection. Please try again.");
        } 
  }
  
  public void remove_from_pending(Appointment appointment) {
    for (Appointment APP: pending) {
      if (APP.equals(appointment)){
        pending.remove(APP);
        break;
      }
    }
  }
  
  public void doctor_accept(Appointment appointment) {
    this.remove_from_pending(appointment);
    accepted.add(appointment);
  }
    
  public void doctor_reject(Appointment appointment) {
    this.remove_from_pending(appointment);
    declined.add(appointment);
  }
    
}


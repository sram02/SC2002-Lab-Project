package hospital;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAppointmentManager {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private ArrayList<Appointment> calendar;
    private ArrayList<Appointment> pending;
    private ArrayList<Appointment> accepted; // for those that the doctor has accepted
    private ArrayList<Appointment> declined; // for those that the doctor has declined, go back to calendar
    private ArrayList<Appointment> completed; // for those that the doctor has completed. It should include AppointmentOutcome

    private Scanner scanner;

    public DAppointmentManager() {
        this.calendar = new ArrayList<>();
        this.pending = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.declined = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // For patient's codes
    public ArrayList<Appointment> get_calendar() {
        return this.calendar;
    }

    public boolean cancel_APT(Appointment appointment) {
        if (pending.remove(appointment)) {
            declined.add(appointment);
            System.out.println("Appointment canceled successfully.");
            return true;
        } else {
            System.out.println("Appointment not found.");
            return false;
        }
    }

    public boolean patient_choose(Appointment appointment) {
        if (calendar.remove(appointment)) {
            pending.add(appointment);
            return true;
        } else {
            System.out.println("Appointment not found.");
            return false;
        }
    }

    public boolean patient_cancel(Appointment appointment) {
        if (accepted.remove(appointment)) {
            declined.add(appointment);
            System.out.println("Appointment canceled successfully.");
            return true;
        } else {
            System.out.println("Appointment not found.");
            return false;
        }
    }

    public boolean patient_reschedule_p1(Appointment appointment) {
        if (accepted.remove(appointment)) {
            calendar.add(appointment);
            return true;
        }
        return false;
    }

    // Method to schedule an appointment
    public void schedule(String doctorID) {
        LocalDate date = null;

        // Get valid date input
        while (date == null) {
            System.out.println("Enter date (yyyy-MM-dd): ");
            String availableDate = scanner.nextLine().trim();

            try {
                // Parse the string into a LocalDate object
                date = LocalDate.parse(availableDate, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }

        // Get valid time input
        String availableTime = "";
        while (availableTime == "") {
            System.out.println("Enter time (hh:mm AM/PM): ");
            availableTime = scanner.nextLine().trim();

            // Regular expression to validate "hh:mm AM/PM" format
            String timePattern = "^(0[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$";
            if (!availableTime.matches(timePattern)) {
                System.out.println("Invalid time format! Please use 'hh:mm AM/PM'.");
                availableTime = "";
                continue;
            }


        }
        // Check for scheduling conflicts
        for (Appointment appt : calendar) {
            if (appt.getDate().equals(date) && appt.getTime().equals(availableTime)) {
                System.out.println("This slot is already taken. Please choose a different time.");
                return;
            }
        }

        // Create and add the appointment
        Appointment appointment = new Appointment("Empty", doctorID, date, availableTime);
        calendar.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public boolean View_list(ArrayList<Appointment> list) {
        if (list.isEmpty()) {
            System.out.println("There are no available appointments.");
            return false;
        } else {
            int index = 0;
            for (Appointment appointment : list) {
                System.out.println(index + ". ");
                appointment.toString();
                index++;
            }
            return true;
        }
    }

    public boolean View_Schedule() {
        return this.View_list(calendar);
    }

    public void View_Upcoming_Appointments() {
        this.View_list(accepted);
    }

    public void View_cancelled() {
        this.View_list(declined);
    }

    public void view_completed() {
        if (completed.isEmpty()) {
            System.out.println("No completed appointments");
            return;
        }
        for (Appointment completed : completed) {
            System.out.println(completed.toString());
            System.out.println(completed.get_AOR().toString());
        }
    }

    public void Accept_Appointments() {
        if (!this.View_list(pending)) return;

        System.out.println("Enter which appointment to Accept/Decline:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer after nextInt()

        Appointment removedItem = pending.remove(index);

        while (true) {
            System.out.println("Would you like to accept that appointment request? (Y/N)");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                removedItem.accept();
                accepted.add(removedItem);

                HospitalManagementSystem hms = HospitalManagementSystem.getInstance();
                Patient patient = hms.getPatientById(removedItem.getPatientID());
                patient.get_PAM().doctor_accept(removedItem);
                break;

            } else if (input.equalsIgnoreCase("N")) {
                removedItem.cancel();
                declined.add(removedItem);

                HospitalManagementSystem hms = HospitalManagementSystem.getInstance();
                Patient patient = hms.getPatientById(removedItem.getPatientID());
                patient.get_PAM().doctor_reject(removedItem);
                break;

            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }

    public void Fill_Completed_Appointment() {
        this.View_list(accepted);
        int index = getCompletedAppointmentIndex();
        Appointment removedItem = removeAcceptedAppointment(index);

        HospitalManagementSystem hms = HospitalManagementSystem.getInstance();
        String diagnosis = getInput("Enter Diagnosis: ");
        String treatment = getInput("Enter Treatment: ");
        String medName = getInput("Enter the Prescribed Medicine Name: ");
        int medQuantity = getMedicineQuantity();

        removedItem.create_AOR(diagnosis, treatment);
        removedItem.get_AOR().set_prescription(medName, medQuantity);
        AppointmentOutcomeManager manager = AppointmentOutcomeManager.getInstance();
        manager.add_AOR(removedItem.get_AOR());
        this.completed.add(removedItem);

        Patient patient = hms.getPatientById(removedItem.getPatientID());
        patient.get_RM().add_to_Completed(removedItem);
        System.out.println("Successfully completed the appointment and requested medication from pharmacy.");
    }

    private int getCompletedAppointmentIndex() {
        System.out.println("Enter which appointment have you completed:");
        int index = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        return index;
    }

    private Appointment removeAcceptedAppointment(int index) {
        Appointment removedItem = accepted.remove(index);
        removedItem.complete();
        return removedItem;
    }

    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private int getMedicineQuantity() {
        System.out.println("Enter the quantity of medicine: ");
        return scanner.nextInt();
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}

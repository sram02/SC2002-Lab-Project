package hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Pharmacist> pharmacists;
    private List<Administrator> administrators;
    private Inventory inventory;  
    private StaffManager staffManager;
    private AdminInventoryManager adminInventoryManager; 

    public HospitalManagementSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.pharmacists = new ArrayList<>();
        this.administrators = new ArrayList<>();
        this.inventory = new Inventory("Medicine_List.csv"); 
        this.staffManager = new StaffManager("Staff_List.csv");
        this.adminInventoryManager = new AdminInventoryManager(inventory);  
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }

    public AdminInventoryManager getAdminInventoryManager() {
        return adminInventoryManager;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    public List<Patient> getPatients() {
        return this.patients;
    }
    
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPharmacist(Pharmacist pharmacist) {
        pharmacists.add(pharmacist);
    }

    public void addAdministrator(Administrator administrator) {
        administrators.add(administrator);
    }

    public void loadData() {
        System.out.println("Loading data...");
        loadPatientsFromCSV("Patient_List.csv");
        loadStaffFromCSV("Staff_List.csv");
        loadMedicinesFromCSV("Medicine_List.csv");
        System.out.println("Data loaded successfully.");
    }

    private void loadPatientsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = br.readLine(); 

            RecordManager recordManager = new RecordManager();
            PAppointmentManager patientAppointmentManager = new PAppointmentManager();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    String userID = values[0].trim();
                    String name = values[1].trim();
                    String dateOfBirth = values[2].trim();
                    String gender = values[3].trim();
                    String bloodType = values[4].trim();
                    String contactInfo = values[5].trim();

                    Patient patient = new Patient(userID, "password", name, gender, 
                                                  dateOfBirth, contactInfo, "", bloodType, 
                                                  recordManager, patientAppointmentManager);
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading patients from CSV: " + e.getMessage());
        }
    }

    private void loadStaffFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5) {  
                    String userID = values[0].trim();
                    String name = values[1].trim();
                    String role = values[2].trim();
                    String gender = values[3].trim();
                    int age = Integer.parseInt(values[4].trim()); 

                    String password = "password"; // Default password

                    switch (role) {
                        case "Doctor":
                            Doctor doctor = new Doctor(userID, password, name, gender, age);
                            doctors.add(doctor);
                            staffManager.addStaff(doctor);
                            break;
                        case "Pharmacist":
                            InventoryManager inventoryManager = new InventoryManager(inventory);
                            Pharmacist pharmacist = new Pharmacist(userID, password, name, gender, age, inventoryManager);
                            pharmacists.add(pharmacist);
                            staffManager.addStaff(pharmacist);
                            break;
                        case "Administrator":
                            Administrator admin = new Administrator(userID, password, name, gender, age, this.adminInventoryManager); // Use this.adminInventoryManager
                            administrators.add(admin);
                            staffManager.addStaff(admin);
                            break;
                        default:
                            System.out.println("Unknown role: " + role);
                            break;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading staff from CSV: " + e.getMessage());
        }
    }

    private void loadMedicinesFromCSV(String filePath) {
        inventory.getMedicines().clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String name = values[0].trim();
                    int initialStock = Integer.parseInt(values[1].trim());
                    int lowStockAlert = Integer.parseInt(values[2].trim());

                    Medicine medicine = new Medicine(name, initialStock, lowStockAlert);
                    inventory.addMedicine(medicine);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading medicines from CSV: " + e.getMessage());
        }
    }

    public User login(String userID, String password) {
        for (Patient patient : patients) {
            if (patient.getUserID().equals(userID) && patient.getPassword().equals(password)) {
                System.out.println("Patient login successful!\n");
                return patient;
            }
        }

        for (Doctor doctor : doctors) {
            if (doctor.getUserID().equals(userID) && doctor.getPassword().equals(password)) {
                System.out.println("Doctor login successful!\n");
                return doctor;
            }
        }

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getUserID().equals(userID) && pharmacist.getPassword().equals(password)) {
                System.out.println("Pharmacist login successful!\n");
                return pharmacist;
            }
        }

        for (Administrator admin : administrators) {
            if (admin.getUserID().equals(userID) && admin.getPassword().equals(password)) {
                System.out.println("Administrator login successful!\n");
                return admin;
            }
        }

        return null;
    }
}

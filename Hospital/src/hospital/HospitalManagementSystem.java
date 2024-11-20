package hospital; 
 
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
 
public class HospitalManagementSystem { 
 
    private static HospitalManagementSystem instance; 
 
    private static List<Patient> patients; 
    private static Inventory inventory; 
    private static StaffManager staffManager; 
    private static AdminInventoryManager adminInventoryManager; 
 
    public HospitalManagementSystem() { 
        setPatients(new ArrayList<>()); 
    } 
 
    public static synchronized HospitalManagementSystem getInstance() { 
        if (instance == null) { 
            instance = new HospitalManagementSystem(); 
        } 
        return instance; 
    } 
 
    public User login(String userID, String password) { 
        for (Patient patient : patients) { 
            if (patient.getUserID().equals(userID) && patient.getPassword().equals(password)) { 
                return patient; 
            } 
        } 
 
        List<Staff> staffList = StaffManager.getInstance("").getAllStaff(); 
        for (Staff staff : staffList) { 
            if (staff.getUserID().equals(userID) && staff.getPassword().equals(password)) { 
                return staff; 
            } 
        } 
 
        System.out.println("No match found for userID: " + userID + " with password: " + password); 
        return null; 
    } 
 
    public void loadData() { 
        System.out.println("Loading data..."); 
        loadPatientsFromCSV("Patient_List.csv"); 
        setInventory(new Inventory("Medicine_List.csv")); 
        setStaffManager(StaffManager.getInstance("Staff_List.csv")); 
        setAdminInventoryManager(new AdminInventoryManager(getInventory())); 
        System.out.println("Data loaded successfully."); 
    } 
 
    public void loadPatientsFromCSV(String filePath) { 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line; 
            line = br.readLine(); // Skip the header 
 
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
                            dateOfBirth, "", contactInfo, bloodType); 
                    patients.add(patient); 
                } 
            } 
        } catch (IOException e) { 
            System.out.println("Error loading patients from CSV: " + e.getMessage()); 
        } 
    } 
 
    public void addDoctor(Doctor doctor) { 
        StaffManager.getInstance("").addStaff(doctor); 
        System.out.println("Doctor added successfully.\n"); 
    } 
 
    public void addPharmacist(Pharmacist pharmacist) { 
        StaffManager.getInstance("").addStaff(pharmacist); 
        System.out.println("Pharmacist added successfully.\n"); 
    } 
 
    public void addAdministrator(Administrator administrator) { 
        StaffManager.getInstance("").addStaff(administrator); 
        System.out.println("Administrator added successfully.\n"); 
    } 
 
    public Patient getPatientById(String userID) { 
        for (Patient patient : patients) { 
            if (patient.getUserID().equals(userID)) { 
                return patient; 
            } 
        } 
        return null; 
    } 
 
    public static Inventory getInventory() { 
        return inventory; 
    } 
 
    public static void setInventory(Inventory inventory) { 
        HospitalManagementSystem.inventory = inventory; 
    } 
 
    public static StaffManager getStaffManager() { 
        return staffManager; 
    } 
 
    public static void setStaffManager(StaffManager staffManager) {
    	HospitalManagementSystem.staffManager = staffManager; 
    } 
 
    public static AdminInventoryManager getAdminInventoryManager() { 
        return adminInventoryManager; 
    } 
 
    public static void setAdminInventoryManager(AdminInventoryManager adminInventoryManager) { 
        HospitalManagementSystem.adminInventoryManager = adminInventoryManager; 
    } 
 
    public static void setPatients(List<Patient> patients) { 
        HospitalManagementSystem.patients = patients; 
    } 
 
    public static List<Patient> getPatients() { 
        return patients; 
    } 
     
    public static void add_Patient(Patient patient) { 
     patients.add(patient); 
    } 
}
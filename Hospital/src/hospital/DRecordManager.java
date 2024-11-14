package hospital;

import java.util.ArrayList;
import java.util.Scanner;

public class DRecordManager {
	
	Scanner scanner = new Scanner(System.in);
	
	private ArrayList<Patient> patients; //for upcoming appointment classes which are set as
	
	//constructor
    public DRecordManager() {
    	this.patients = new ArrayList<>();
    }

    public void viewPatientMedicalRecord() {
    	int index = 0;
        for (Patient patient : patients) {
        	System.out.println("Patient name: " + patient.getName());
        	System.out.println("Patient ID: " + patient.getUserID());
        	       	
        }
    }
    
    public void UpdatePatientRecord() {
    	//unsure portion
    }
}

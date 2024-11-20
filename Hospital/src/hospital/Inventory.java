package hospital;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    private List<Medicine> medicines;

    public Inventory() { //the only one in the game
        this.medicines = new ArrayList<>();
    }

    // load medicines from a CSV file
    public Inventory(String filePath) {
        this.medicines = new ArrayList<>();
        loadMedicinesFromCSV(filePath);
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public Medicine getMedicineByName(String name) {
        for (Medicine medicine : medicines) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null; 
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    // load medicines from CSV file
    private void loadMedicinesFromCSV(String filePath) {
    	medicines.clear();
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
                    medicines.add(medicine);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading medicines from CSV: " + e.getMessage());
        }
    }
}

package hospital;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InventoryManager {
    private Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public boolean takeMedicine(String MedName, int quantity) {
        
    	Medicine medicine = this.inventory.getMedicineByName(MedName);
    	
    	//case 1: Medicine not found
    	if (medicine == null){
        	System.out.println("Medicine not found in inventory. ");
        	return false;
        }
    	//case 2: Medicine is at the low level stock level. Urge to submit request, but successfully taken medicine.
    	else if (medicine.getStockLevel() - quantity < medicine.getLowStockThreshold()) {
    		medicine.setStockLevel(medicine.getStockLevel() - quantity);
    		System.out.println(MedName + " quantity is now below low stock threshold. You are urged to submit a replenishment request.");
    		this.saveInventoryToCSV();
    		return true;
    	}	
    	//case 3: Medicine is not enough. Return false and don't do anything
    	else if (medicine.getStockLevel() < quantity) {
    		System.out.println("Stock level is too low. Please request for a replenishment for " + MedName);
    		return false;
    	}
    	//case 4: Medicine is enough. Successfully taken medicine
    	else {
    		medicine.setStockLevel(medicine.getStockLevel() - quantity);
    		this.saveInventoryToCSV();
    		return true;
    	}
    }

	public void saveInventoryToCSV() {
	    try (FileWriter writer = new FileWriter("Medicine_List.csv")) {
	        writer.write("Name,StockLevel,LowStockThreshold\n"); // CSV Header
	        for (Medicine medicine : inventory.getMedicines()) {
	            writer.write(medicine.getName() + "," + medicine.getStockLevel() + "," + medicine.getLowStockThreshold() + "\n");
	        }
	        System.out.println("Inventory data saved to Medicine_List.csv");
	    } catch (IOException e) {
	        System.out.println("Error saving inventory data to CSV: " + e.getMessage());
	    }
	}
	
    public void viewInventory() {
        System.out.println("\nInventory:");
        List<Medicine> medicines = inventory.getMedicines();
        
        if (medicines.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            for (Medicine medicine : medicines) {
                System.out.print("Medicine: " + medicine.getName() + 
                                 ", Stock Level: " + medicine.getStockLevel() + 
                                 ", Low Stock Alert: " + medicine.getLowStockThreshold());
                
                // Check if stock level is below threshold
                if (medicine.getStockLevel() < medicine.getLowStockThreshold()) {
                    System.out.print(" - WARNING: Stock level is low!");
                }
                System.out.println(); 
            }
        }
        
        System.out.println("\nReturning to Menu...\n");
    }


    public void submitReplenishmentRequest(ReplenishmentRequest request, AdminInventoryManager adminInventoryManager) {
        adminInventoryManager.addReplenishmentRequest(request);
        System.out.println("Replenishment request submitted for: " + request.getMedicineName());
        System.out.print("\nReturning to Menu..\n\n");
    }
}

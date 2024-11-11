package hospital;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Medicine> medicines;

    public Inventory() {
        this.medicines = new ArrayList<>();
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
        return null; // Return null if not found
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
}

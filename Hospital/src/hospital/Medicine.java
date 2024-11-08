package hospital;

public class Medicine {
    private String name;
    private int stockLevel;
    private int lowStockThreshold;

    public Medicine(String name, int stockLevel, int lowStockThreshold) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getName() { return name; }
    public int getStockLevel() { return stockLevel; }
    public int getLowStockThreshold() { return lowStockThreshold; }

    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }

    public boolean isStockLow() {
        return stockLevel < lowStockThreshold;
    }

    @Override
    public String toString() {
        return "Medicine Name: " + name + ", Stock Level: " + stockLevel + ", Low Stock Threshold: " + lowStockThreshold;
    }
}

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

    public String getName() {
        return name;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public void increaseStock(int amount) {
        this.stockLevel += amount;
    }

    @Override
    public String toString() {
        return "Medicine: " + name + ", Stock Level: " + stockLevel + ", Low Stock Alert: " + lowStockThreshold;
    }
}

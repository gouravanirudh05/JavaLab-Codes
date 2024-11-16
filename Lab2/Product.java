// Abstract base class for all types of products in the store
public abstract class Product {
    // Basic product attributes
    private String itemId;     // Unique identifier for the product
    private String name;       // Name of the product
    private double price;      // Price of the product
    private int quantity;      // Available quantity in stock
    // Constructor with all product details
    public Product(String itemId, String name, double price, int quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    // Default constructor initializing with empty/zero values
    public Product() 
    {
        this.itemId = "";
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
    }
    // Getters and setters for product attributes
    public String getItemId() {
        return itemId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to reduce stock of the product
    public boolean reduceStock(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
            return true;
        }
        return false;
    }
    // Abstract method for product-specific details
    public abstract String getAdditionalDetails();
}
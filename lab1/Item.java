public class Item {
    // Private fields for encapsulation
    private String itemId;
    private String name;
    private double price;
    private int quantity;
    // Constructor to initialize item details
    public Item(String itemID,String name, double price, int quantity) {
        this.itemId=itemID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    // Default constructor to initialize item details with empty strings and zero values
    public Item() {
        this.itemId = "";
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
    }
    // Getters for item details
    public String getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // toString method to format item details
    @Override
    public String toString() {
        return itemId + " " + name + " " + price + " " + quantity;
    }
    //Setter methods
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
}
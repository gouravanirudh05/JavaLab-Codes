// Class representing fashion/clothing products, extends the base Product class
public class Fashion extends Product {
    private String size;// Stores the size of the fashion item
    // Constructor to create a new fashion product
    public Fashion(String itemId, String name, double price, int quantity, String size) {
        super(itemId, name, price, quantity);
        this.size = size;
    }
    // Returns the size of the fashion item
    public String getSize() {
        return size;
    }
    // Sets the size of the fashion item
    public void setSize(String size) {
        this.size = size;
    }
    // Returns a string representation of the fashion product
    @Override
    public String toString() {
        return getItemId() + " " + getName()+" "+getPrice()+" ";
    }
    // Returns size information as additional product details
    @Override
    public String getAdditionalDetails() {
        return "(Size: " + size + ")";
    }
}
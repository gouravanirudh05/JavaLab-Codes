// Class representing electronic products, extends the base Product class
public class Electronics extends Product {
    private int warrantyPeriod;
    // Constructor to create a new electronic product
    public Electronics(String itemId, String name, double price, int quantity, int warrantyPeriod) {
        super(itemId, name, price, quantity);
        this.warrantyPeriod = warrantyPeriod; // Stores the warranty period in months
    }
    //Get the warranty period
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    // Sets the warranty period
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    // Returns warranty information as additional product details
    @Override
    public String toString() {
        return getItemId() + " " + getName()+ " " + getPrice()+" ";
    }
    // Returns warranty information as additional product details
    @Override
    public String getAdditionalDetails() {
        return "(" + warrantyPeriod + " months warranty)";
    }
}

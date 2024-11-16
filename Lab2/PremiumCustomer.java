// Class representing a premium customer with discount benefits
public class PremiumCustomer extends Customer {
    private double discount=0.10; // Stores the discount rate (default 10%)
     // Constructor to create a new premium customer
    public PremiumCustomer(String customerId, String name, String email, String address) {
        super(customerId, name, email, address);
    }
    // Returns the customer's discount rate
    public double getDiscount() {
        return discount;
    }
    
    // Sets the customer's discount rate
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    // Calculates total price after applying premium discount
    public double calculateTotalPrice() {
        double totalPrice = getCart().calculateTotal()*(1-getDiscount());
        return totalPrice;
    }
    // Returns the customer type as "Premium"
    @Override
    public String getCustomerType() {
        return "Premium";
    }
}

// Class representing a regular customer with standard pricing
public class RegularCustomer extends Customer {
    // Constructor to create a new regular customer
    public RegularCustomer(String customerId, String name, String email, String address) {
        super(customerId, name, email, address);
    }
    // Calculates total price with no special discounts
    @Override
    public double calculateTotalPrice() {
        return getCart().calculateTotal();
    }
    // Returns the customer type as "Regular"
    @Override
    public String getCustomerType() {
        return "Regular";
    }  
}
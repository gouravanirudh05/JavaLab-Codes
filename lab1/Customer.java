public class Customer {
    // Private fields for encapsulation
    private String customerId;
    private String name;
    private String email;
    private String address;
    private ShoppingCart shoppingCart;

    // Constructor to initialize customer details and an empty ShoppingCart
    public Customer(String customerId, String name, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.shoppingCart = new ShoppingCart(); 
    }
    //Default Constructor
    public Customer()
    {
        customerId = "";
        name = "";
        email = "";
        address = "";
        shoppingCart = new ShoppingCart(); 
    }
    //Getter methods
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getCustomerId()
    {
        return customerId;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public ShoppingCart getCart()
    {
        return shoppingCart;
    }
    //Setter methods
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setName(String name) {
        this.name = name;
    }
}

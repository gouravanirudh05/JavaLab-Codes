public abstract class Customer {
    private String customerId;
    private String name;
    private String email;
    private String address;
    private ShoppingCart cart;  
    public Customer(String customerId, String name, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.cart = new ShoppingCart();//new ShoppingCart  
    }
    /**
     * Default constructor
     * Initializes all string fields to empty strings and creates a new shopping cart
     */
    public Customer()
    {
        customerId = "";
        name = "";
        email = "";
        address = "";
        cart = new ShoppingCart(); 
    }
    //getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShoppingCart getCart() {
        return cart;
    }
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    //Adding Product to Cart
    public void addToCart(Product product) {
        cart.addItem(product);
    }
    //Abstract methods that depend on the type of customer
    public abstract double calculateTotalPrice();
    public abstract String getCustomerType();
}

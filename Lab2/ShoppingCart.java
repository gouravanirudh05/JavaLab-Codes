import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Class representing a shopping cart that holds product items
public class ShoppingCart {
    // List to store products in the cart
    private List<Product> items;
    private HashMap<String, Integer> quantityInCart;

    // Constructor to create a new empty shopping cart
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.quantityInCart = new HashMap<>();
    }

    // Adds a product to the cart
    public void addItem(Product product) {
        items.add(product);
        quantityInCart.put(product.getItemId(), quantityInCart.getOrDefault(product.getItemId(), 0) + 1);
    }

    // Returns the list of items in the cart
    public List<Product> getItems() {
        return items;
    }

    // Sets the list of items in the cart
    public void setItems(List<Product> items) {
        this.items = items;
    }

    // Calculates the total price of all items in the cart
    public double calculateTotal() {
        double total = 0;
        for (Product product : items) {
            total += product.getPrice();
        }
        return total;
    }

    // Removes all items from the cart
    public void emptyCart() {
        items.clear();
        quantityInCart.clear();
    }

    // Checks if the cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Returns the quantity of a specific item in the cart
    public int getQuantityInCart(String itemId) {
        return quantityInCart.getOrDefault(itemId, 0);
    }
    public List<Product> getUniqueItems() {
        HashMap<String, Product> uniqueProducts = new HashMap<>();
        for (Product product : items) {
            uniqueProducts.put(product.getItemId(), product);
        }
        return new ArrayList<>(uniqueProducts.values());
    }
}

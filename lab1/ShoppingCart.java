import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    // Private field to store a list of Item objects
    private List<Item> items;

    // Constructor to initialize the items list
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    //Getter methods
    public List<Item> getItems() {
        return items;
    }
    //Setter methods
    public void setItems(List<Item> items) {
        this.items = items;
    }
    // Method to add an item to the cart
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to remove an item by its ID
    public void removeItem(String itemId) {
        for(Item item:items)
        {
            if(item.getItemId()==itemId)
            {
                items.remove(item);
                break;
            }
        }
    }
    // Method to view all items in the cart
    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }
    // Method to calculate the total price of items in the cart
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }    
}

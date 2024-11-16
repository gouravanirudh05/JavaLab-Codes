import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Helper {
    private PrintStream out;

    public Helper(PrintStream out) {
        this.out = out;
    }

    public void print(String message) {
        out.println(message);
    }

    public void displayCart(String customerId, ShoppingCart cart) {
        print("Items in Cart for " + customerId + ":");
        for (Item item : cart.getItems()) {
            print(item.toString());
        }
    }

    public void displayTotalPrice(String customerId, double total) {
        print("Total Price for " + customerId + ": " + total);
    }

    public void displayCustomerInfo(Customer customer) {
        print("Customer Info for " + customer.getCustomerId() + ":\nName: " + customer.getName() + "\nAddress: " + customer.getAddress());
    }

    public void processCommands(Scanner scanner, HashMap<String, Customer> customers, HashMap<String, Item> inventory) {
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] inputs = parseCommand(command);

            switch (inputs[0]) {
                case "ADD_ITEM":
                    String itemId = inputs[1];
                    String name = inputs[2];
                    double price = Double.parseDouble(inputs[3]);
                    int quantity = Integer.parseInt(inputs[4]);
                    Item item = new Item(itemId, name, price, quantity);
                    inventory.put(itemId, item);
                    print("Item added to inventory: " + item);
                    break;

                case "ADD_CUSTOMER":
                    String customerId = inputs[1];
                    String customerName = inputs[2];
                    String email = inputs[3];
                    String address = inputs[4];
                    Customer customer = new Customer(customerId, customerName, email, address);
                    customers.put(customerId, customer);
                    print("Customer added: " + customerId + " \"" + customerName + "\" \"" + email + "\" \"" + address + "\"");
                    break;

                case "ADD_TO_CART":
                    customerId = inputs[1];
                    itemId = inputs[2];
                    customer = customers.get(customerId);
                    item = inventory.get(itemId);
                    if (customer != null && item != null) {
                        customer.getCart().addItem(item);
                        print("Item " + itemId + " added to " + customerId + "'s cart");
                    } else {
                        print("Invalid customerId or itemId");
                    }
                    break;

                case "VIEW_CART":
                    customerId = inputs[1];
                    customer = customers.get(customerId);
                    if (customer != null) {
                        displayCart(customerId, customer.getCart());
                    } else {
                        print("Customer not found");
                    }
                    break;

                case "TOTAL_PRICE":
                    customerId = inputs[1];
                    customer = customers.get(customerId);
                    if (customer != null) {
                        double total = customer.getCart().calculateTotal();
                        displayTotalPrice(customerId, total);
                    } else {
                        print("Customer not found");
                    }
                    break;

                case "VIEW_CUSTOMER":
                    customerId = inputs[1];
                    customer = customers.get(customerId);
                    if (customer != null) {
                        displayCustomerInfo(customer);
                    } else {
                        print("Customer not found");
                    }
                    break;

                case "EXIT":
                    scanner.close();
                    return;

                default:
                    print("Invalid command");
            }
        }
    }

    // Helper method to parse commands with quoted strings
    private String[] parseCommand(String command) {
        ArrayList<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(command);
        
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Add quoted strings
                tokens.add(matcher.group(1));
            } else {
                // Add unquoted words
                tokens.add(matcher.group(2));
            }
        }
        
        return tokens.toArray(new String[0]);
    }
}

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
        for (Product item : cart.getUniqueItems()) {
            String itemDetails = item.toString()+ cart.getQuantityInCart(item.getItemId()) + " " + item.getAdditionalDetails();
            print(itemDetails);
        }
    }
    public void displayTotalPrice(String customerId,String customerType,double total){
        if(customerType=="Regular")
        print("Total Price for " + customerId + ": " + total);
        else if(customerType=="Premium")
        print("Total Price for " + customerId+" (with discount): " + total);
    } 
    public void displayCustomerInfo(Customer customer) {
        print("Customer Info for " + customer.getCustomerId()+" "+"("+customer.getCustomerType()+")"+ ":\nName: " + customer.getName() + "\nAddress: " + customer.getAddress());
    }

    public void processCommands(Scanner scanner, HashMap<String, Customer> customers, HashMap<String, Product> inventory) {
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] inputs = parseCommand(command);

            switch (inputs[0]) {

                case "ADD_ELECTRONICS":
                    String itemId = inputs[1];
                    String name = inputs[2];
                    double price = Double.parseDouble(inputs[3]);
                    int quantity = Integer.parseInt(inputs[4]);
                    int warranty = Integer.parseInt(inputs[5]);
                    Electronics ElectronicsItem = new Electronics(itemId, name, price, quantity,warranty);
                    inventory.put(itemId, ElectronicsItem);
                    print("Electronics Item added: "+ElectronicsItem.getItemId()+" "+ElectronicsItem.getName());
                    break;
                case "ADD_FASHION":
                    itemId = inputs[1];
                    name = inputs[2];
                    price = Double.parseDouble(inputs[3]);
                    quantity = Integer.parseInt(inputs[4]);
                    String size = inputs[5];
                    Fashion fashionItem = new Fashion(itemId, name, price, quantity,size);
                    inventory.put(itemId, fashionItem);
                    print("Fashion Item added: "+fashionItem.getItemId()+" "+fashionItem.getName());
                    break;
                case "ADD_REGULAR_CUSTOMER":
                    String customerId = inputs[1];
                    String customerName = inputs[2];
                    String email = inputs[3];
                    String address = inputs[4];
                    RegularCustomer regularCustomer = new RegularCustomer(customerId, customerName, email, address);
                    customers.put(customerId, regularCustomer);
                    print("Regular Customer added: " + customerId + " " + customerName);
                    break;
                case "ADD_PREMIUM_CUSTOMER":
                    customerId = inputs[1];
                    customerName = inputs[2];
                    email = inputs[3];
                    address = inputs[4];
                    PremiumCustomer premiumCustomer = new PremiumCustomer(customerId, customerName, email, address);
                    customers.put(customerId, premiumCustomer);
                    print("Premium Customer added: " + customerId + " " + customerName);
                    break;
                case "ADD_TO_CART":
                    customerId = inputs[1];
                    itemId = inputs[2];
                    Customer customer = customers.get(customerId);
                    Product item = inventory.get(itemId);
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
                        double total = customer.calculateTotalPrice();
                        displayTotalPrice(customerId,customer.getCustomerType(),total);
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

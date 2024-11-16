import java.util.HashMap;
import java.util.Scanner;
// Main Application Class
public class Main {
    public static void main(String[] args) {
        HashMap<String, Customer> customers = new HashMap<>();
        HashMap<String, Product> inventory = new HashMap<>();
        Helper helper = new Helper(System.out);
        Scanner scanner = new Scanner(System.in);
        helper.processCommands(scanner, customers, inventory);
    }
}

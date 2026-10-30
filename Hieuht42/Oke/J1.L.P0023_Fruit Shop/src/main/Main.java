package main;

import java.util.ArrayList;
import model.Fruit;
import model.OrderItem;
import utils.Helper;
import utils.Validate;

public class Main {

    private static final ShopManager MANAGER = new ShopManager();

    public static void main(String[] args) {
        while (true) {
            Helper.menu();
            int choice = Validate.getInt("Enter your choice: ", 1, 4);
            switch (choice) {
                case 1:
                    createFruit();
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    shopping();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void createFruit() {
        while (true) {
            String fruitId = Validate.getString("Enter fruit id: ");
            if (MANAGER.isExistId(fruitId)) {
                System.out.println("Fruit id already exists.");
                continue;
            }
            String fruitName = Validate.getString("Enter fruit name: ");
            double price = Validate.getPrice("Enter price: ");
            int quantity = Validate.getPositiveInt("Enter quantity: ");
            String origin = Validate.getString("Enter origin: ");
            MANAGER.addFruit(new Fruit(fruitId, fruitName, price, quantity, origin));
            System.out.println("Create fruit successfully.");
            if (Validate.getOption("Do you want to continue (Y/N)? ", "Y", "N").equals("N")) {
                displayAllFruits();
                return;
            }
        }
    }

    private static void displayAllFruits() {
        System.out.printf("%-10s%-15s%-10s%-10s%s%n", "Fruit Id", "Fruit Name", "Price", "Quantity", "Origin");
        for (Fruit fruit : MANAGER.getFruits()) {
            System.out.printf("%-10s%-15s%-10s%-10d%s%n", fruit.getFruitId(), fruit.getFruitName(),
                    Helper.formatMoney(fruit.getPrice()), fruit.getQuantity(), fruit.getOrigin());
        }
    }

    private static void viewOrders() {
        if (MANAGER.getOrders().isEmpty()) {
            System.out.println("There is no order.");
            return;
        }
        for (String customer : MANAGER.getOrders().keySet()) {
            System.out.println("Customer: " + customer);
            displayItems(MANAGER.getOrders().get(customer), true);
        }
    }

    private static void shopping() {
        if (MANAGER.getFruits().isEmpty()) {
            System.out.println("There is no fruit in the shop.");
            return;
        }
        ArrayList<OrderItem> cart = new ArrayList<>();
        while (true) {
            ArrayList<Fruit> available = MANAGER.getAvailableFruits();
            if (available.isEmpty()) {
                System.out.println("All fruits are out of stock.");
                if (cart.isEmpty()) {
                    return;
                }
                break;
            }
            displayFruitsForShopping(available);
            Fruit fruit = available.get(Validate.getInt("Select item: ", 1, available.size()) - 1);
            System.out.println("You selected: " + fruit.getFruitName());
            int quantity = Validate.getInt("Please input quantity: ", 1, fruit.getQuantity());
            MANAGER.addToCart(cart, fruit, quantity);
            if (Validate.getOption("Do you want to order now (Y/N)? ", "Y", "N").equals("Y")) {
                break;
            }
        }
        displayItems(cart, false);
        String customer = Validate.getString("Input your name: ");
        MANAGER.addOrder(customer, cart);
    }

    private static void displayFruitsForShopping(ArrayList<Fruit> available) {
        System.out.println("List of Fruit:");
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        for (int i = 0; i < available.size(); i++) {
            Fruit fruit = available.get(i);
            System.out.printf("  %-12d%-19s%-15s%s%n", i + 1, fruit.getFruitName(),
                    fruit.getOrigin(), Helper.formatMoney(fruit.getPrice()));
        }
    }

    private static void displayItems(ArrayList<OrderItem> items, boolean numbered) {
        System.out.println("Product | Quantity | Price | Amount");
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            String product = numbered ? (i + 1) + ". " + item.getFruit().getFruitName()
                    : item.getFruit().getFruitName();
            System.out.printf("%-15s%-10d%-10s%s%n", product, item.getQuantity(),
                    Helper.formatMoney(item.getFruit().getPrice()), Helper.formatMoney(item.getAmount()));
        }
        System.out.println("Total: " + Helper.formatMoney(MANAGER.getTotal(items)));
    }
}

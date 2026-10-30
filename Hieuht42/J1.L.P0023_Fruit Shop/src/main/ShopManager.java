package main;

import java.util.ArrayList;
import java.util.Hashtable;
import model.Fruit;
import model.OrderItem;

public class ShopManager {

    private final ArrayList<Fruit> fruits = new ArrayList<>();
    private final Hashtable<String, ArrayList<OrderItem>> orders = new Hashtable<>();

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public Hashtable<String, ArrayList<OrderItem>> getOrders() {
        return orders;
    }

    public boolean isExistId(String fruitId) {
        for (Fruit fruit : fruits) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                return true;
            }
        }
        return false;
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public ArrayList<Fruit> getAvailableFruits() {
        ArrayList<Fruit> result = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() > 0) {
                result.add(fruit);
            }
        }
        return result;
    }

    public void addToCart(ArrayList<OrderItem> cart, Fruit fruit, int quantity) {
        fruit.setQuantity(fruit.getQuantity() - quantity);
        mergeItem(cart, fruit, quantity);
    }

    public void addOrder(String customer, ArrayList<OrderItem> cart) {
        ArrayList<OrderItem> items = orders.get(customer);
        if (items == null) {
            orders.put(customer, cart);
            return;
        }
        for (OrderItem item : cart) {
            mergeItem(items, item.getFruit(), item.getQuantity());
        }
    }

    public double getTotal(ArrayList<OrderItem> items) {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    private void mergeItem(ArrayList<OrderItem> items, Fruit fruit, int quantity) {
        for (OrderItem item : items) {
            if (item.getFruit() == fruit) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new OrderItem(fruit, quantity));
    }
}

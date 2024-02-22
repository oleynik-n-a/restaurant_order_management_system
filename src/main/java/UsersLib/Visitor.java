package UsersLib;

import OrderLib.Order;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    private final ArrayList<Order> _orders;

    public Visitor(String login, String password) {
        super(login, password);
        _orders = new ArrayList<Order>();
    }

    public void makeOrder() {

    }

    public void changeOrder() {

    }

    public void cancelOrder() {

    }

    @Override
    public void showConsoleMenu() {
        System.out.println("Choose option:");
        System.out.println("    1. Make an order.");
        System.out.println("    2. Change order.");
        System.out.println("    3. Cancel order.");
        System.out.println("    4. Log out.");
        System.out.println("    5. Exit system.");
        System.out.print("\nYour choice: ");
    }
}

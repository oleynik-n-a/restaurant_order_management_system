package Program;

import OrderLib.Menu;
import OrderLib.Order;
import UsersLib.User;

import java.util.ArrayList;

public final class ManagementSystem {
    private final ArrayList<Order> _orders;
    private final ArrayList<Thread> _threads;
    private final Menu _menu;
    private final User _user;

    public ManagementSystem(ArrayList<Order> orders, ArrayList<Thread> threads, Menu menu, User user) {
        _orders = orders;
        _threads = threads;
        _menu = menu;
        _user = user;
    }

    public ArrayList<Order> getOrders() {
        return  _orders;
    }

    public Menu getMenu() {
        return _menu;
    }

    public User getUser() {
        return  _user;
    }
}

package Program;

import BuilderLib.IBuilder;
import OrderLib.Menu;
import OrderLib.Order;
import UsersLib.User;

import java.util.ArrayList;

public class ManagementSystemBuilder implements IBuilder {
    private ArrayList<Order> _orders;
    private ArrayList<Thread> _threads;
    private Menu _menu;
    private User _user;

    public void setOrderList(ArrayList<Order> orders) {

    }

    public void setThreads(ArrayList<Order> threads) {

    }

    public void setUser(User user) {

    }

    public void setMenu(Menu menu) {

    }

    @Override
    public ManagementSystem buildPart() {
        return new ManagementSystem(_orders, _threads, _menu, _user);
    }
}

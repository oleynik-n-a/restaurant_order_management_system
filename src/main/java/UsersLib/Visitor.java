package UsersLib;

import OrderLib.Menu;
import OrderLib.Order;
import DishLib.Dish;
import OrderLib.OrderStatus;

import java.util.ArrayList;
import java.util.Scanner;

public class Visitor extends User {
    private final ArrayList<Order> _orders;

    public Visitor(String login, String password) {
        super(login, password);
        _orders = new ArrayList<Order>();
    }

    public void makeOrder(final Menu menu) {
        String input;
        ArrayList<Dish> dishes = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Current order:");
            for (var dish : dishes) {
                System.out.println(dish);
            }
            System.out.println("\nChoose dishes (0 while ready):\n");
            System.out.println(menu);
            System.out.print("\nYour choice: ");

            input = in.nextLine();
            if (isNotInt(input)) {
                System.out.println("Incorrect input.\n");
                continue;
            }

            int position = Integer.parseInt(input);
            if (position == 0) {
                _orders.add(new Order(dishes));
                break;
            }
            if (position < 0) {
                System.out.println("Incorrect input.\n");
                continue;
            }
            if (position > menu.getDishesList().size()) {
                System.out.println("Sorry, there is no such position in our menu.\n");
                continue;
            }
            if (menu.getDishesList().get(position - 1).getAmount() == 0) {
                System.out.println("Sorry, there is no such dish at the moment.\n");
                continue;
            }

            System.out.println();
            dishes.add(menu.getDishesList().get(position - 1));
            menu.getDishesList().get(position - 1).setAmount(menu.getDishesList().get(position - 1).getAmount() - 1);
        }
    }

    public void changeOrder(final Menu menu) {
        String input;
        Scanner in = new Scanner(System.in);

        int position = chooseOrder();
        if (position == 0) {
            System.out.println();
            return;
        }

        while (true) {
            System.out.println(_orders.get(position - 1));
            showChangeOrderMenu();
            input = in.nextLine();

            switch (input) {
                case "1":
                    addDish(menu, _orders.get(position - 1));
                    return;
                case "2":
                    removeDish(_orders.get(position - 1));
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Incorrect input.\n");
            }
        }
    }

    private void showChangeOrderMenu() {
        System.out.println("Choose an option:");
        System.out.println("  1. Add dish.");
        System.out.println("  2. Remove dish.");
        System.out.println("  3. Back.");
        System.out.print("\nYour option:");
    }

    private void addDish(final Menu menu, final Order order) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.print("Choose dish to add: ");
        input = in.nextLine();
        if (isNotInt(input)) {
            System.out.println("Incorrect input.\n");
            return;
        }

        int position = Integer.parseInt(input);
        if (position < 0) {
            System.out.println("Incorrect input.\n");
            return;
        }
        if (position > menu.getDishesList().size()) {
            System.out.println("Sorry, there is no such position in our menu.\n");
            return;
        }
        if (menu.getDishesList().get(position - 1).getAmount() == 0) {
            System.out.println("Sorry, there is no such dish at the moment.\n");
            return;
        }
        if (order.getStatus() == OrderStatus.Ready) {
            System.out.println("Sorry, you can't add a dish when order is ready.\n");
            return;
        }

        System.out.println();
        order.getDishesList().add(menu.getDishesList().get(position - 1));
        menu.getDishesList().get(position - 1).setAmount(menu.getDishesList().get(position - 1).getAmount() - 1);
    }

    private void removeDish(final Order order) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.print("Choose dish to remove: ");
        input = in.nextLine();
        if (isNotInt(input)) {
            System.out.println("Incorrect input.\n");
            return;
        }

        int position = Integer.parseInt(input);
        if (position < 0 || position > order.getDishesList().size()) {
            System.out.println("Incorrect input.\n");
            return;
        }
        if (order.getStatus() == OrderStatus.Ready) {
            System.out.println("Sorry, you can't remove a dish when order is ready.\n");
            return;
        }

        System.out.println();
        order.getDishesList().remove(position - 1);
    }

    public void cancelOrder() {
        int position = chooseOrder();

        if (position == 0) {
            System.out.println();
            return;
        }
        if (_orders.get(position - 1).getStatus() == OrderStatus.Ready) {
            System.out.println("Sorry, you can't cancel an order when it's ready.\n");
            return;
        }

        _orders.remove(position - 1);
        System.out.println("Order canceled.\n");
    }

    private int chooseOrder() {
        String input;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Current orders:");
            for (var order : _orders) {
                System.out.println("  " + order);
            }
            System.out.println("\nChoose an order (0 to return back):\n");
            System.out.print("\nYour choice: ");

            input = in.nextLine();
            if (isNotInt(input)) {
                System.out.println("Incorrect input.\n");
                continue;
            }

            int position = Integer.parseInt(input);
            if (position < 0) {
                System.out.println("Incorrect input.\n");
                continue;
            }
            if (position > _orders.size()) {
                System.out.println("Sorry, there is no such order with such position.\n");
                continue;
            }

            return position;
        }
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

package UsersLib;

import DishLib.Review;
import OrderLib.Kitchen;
import OrderLib.Menu;
import OrderLib.Order;
import DishLib.Dish;
import OrderLib.OrderStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Visitor extends User implements Serializable {
    private int _bills;
    private final ArrayList<Order> _orders;

    public Visitor(String login, String password) {
        super(login, password);
        _orders = new ArrayList<>();
        _bills = 0;
    }

    public void makeOrder(final Menu menu) {
        String input;
        ArrayList<Dish> dishes = new ArrayList<>();
        final Scanner in = new Scanner(System.in);

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
                System.out.println("\nIncorrect input.");
                continue;
            }

            int position = Integer.parseInt(input);
            if (position == 0) {
                if (dishes.isEmpty()) {
                    System.out.println("\nSorry, you can't make an empty order.");
                    continue;
                }
                _orders.add(new Order(dishes));
                break;
            }
            if (position < 0) {
                System.out.println("'\nIncorrect input.");
                continue;
            }
            if (position > menu.getDishesList().size()) {
                System.out.println("\nSorry, there is no such position in our menu.");
                continue;
            }
            if (menu.getDishesList().get(position - 1).getAmount() == 0) {
                System.out.println("\nSorry, there is no such dish at the moment.");
                continue;
            }

            System.out.println();
            dishes.add(menu.getDishesList().get(position - 1));
            menu.getDishesList().get(position - 1).setAmount(menu.getDishesList().get(position - 1).getAmount() - 1);
        }

        Order order = new Order(dishes);
        _orders.add(order);
        Kitchen.getInstance().pushOrder(order);
        _bills += order.getCost();
    }

    public void changeOrder(final Menu menu) {
        String input;
        final Scanner in = new Scanner(System.in);

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
        final Scanner in = new Scanner(System.in);

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
        if (order.getStatus() == OrderStatus.Ready || order.getStatus() == OrderStatus.Paid) {
            System.out.println("Sorry, you can't add a dish when order is ready or paid.\n");
            return;
        }

        System.out.println();
        order.getDishesList().add(menu.getDishesList().get(position - 1));
        order.setTimeLeft(order.getTimeLeft() + menu.getDishesList().get(position - 1).getCookingTime());
        menu.getDishesList().get(position - 1).setAmount(menu.getDishesList().get(position - 1).getAmount() - 1);
    }

    private void removeDish(final Order order) {
        String input;
        final Scanner in = new Scanner(System.in);

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
        if (order.getStatus() == OrderStatus.Ready || order.getStatus() == OrderStatus.Paid) {
            System.out.println("Sorry, you can't remove a dish when order is ready or paid.\n");
            return;
        }

        System.out.println();
        order.setTimeLeft(Math.max(order.getTimeLeft() - order.getDishesList().get(position - 1).getCookingTime(), 0));
        order.getDishesList().remove(position - 1);
    }

    public void cancelOrder() {
        int position = chooseOrder();

        if (position == 0) {
            System.out.println();
            return;
        }
        if (_orders.get(position - 1).getStatus() == OrderStatus.Ready ||
                _orders.get(position - 1).getStatus() == OrderStatus.Paid) {
            System.out.println("Sorry, you can't cancel an order when it's ready or paid.\n");
            return;
        }

        _orders.remove(position - 1);
        System.out.println("Order canceled.\n");
    }

    private int chooseOrder() {
        String input;
        final Scanner in = new Scanner(System.in);

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

    private void payBill(final Menu menu) {
        final Scanner in = new Scanner(System.in);
        ArrayList<Order> unpaidOrders = new ArrayList<>();
        int option;
        String input;

        for (var order : _orders) {
            if (order.getStatus() == OrderStatus.Ready) {
                unpaidOrders.add(order);
            }
        }

        if (unpaidOrders.isEmpty()) {
            System.out.println("\nAll bills are paid.");
            return;
        }

        while (true) {
            showBillPaymentMenu(unpaidOrders);
            input = in.nextLine();

            if (isNotInt(input)) {
                System.out.println("\nIncorrect input.");
                continue;
            }

            option = Integer.parseInt(input);
            if (option == 0) {
                return;
            }
            if (option > unpaidOrders.size()) {
                System.out.println("\nThere is no such option.");
                continue;
            }

            unpaidOrders.get(option - 1).updateStatus();
            System.out.println("\nBill successfully paid.\n");
            break;
        }

        System.out.println("\nType 'review' if you want to make review on dishes: ");
        input = in.nextLine();
        if (!Objects.equals(input, "review")) {
            System.out.println();
            for (int i = 0, j = 0; i < _orders.size(); ++i) {
                if (_orders.get(i).getStatus() == OrderStatus.Paid) {
                    ++j;
                    if (option == j) {
                        _orders.remove(i);
                    }
                }
            }
            return;
        }

        int position = 0;
        for (int i = 0, j = 0; i < _orders.size(); ++i) {
            if (_orders.get(i).getStatus() == OrderStatus.Paid) {
                ++j;
                if (option == j) {
                    position = i;
                    break;
                }
            }
        }
        makeReviews(menu, position);

        _orders.remove(position);
    }

    private void showBillPaymentMenu(ArrayList<Order> unpaidOrders) {
        System.out.println("Choose one of unpaid bills to pay for it (0 to return back):");
        for (int i = 0; i < unpaidOrders.size(); ++i) {
            System.out.println("  " + (i + 1) + ". " + unpaidOrders.get(i));
        }
        System.out.print("\nYour choice: ");
    }

    private void makeReviews(final Menu menu, int position) {
        final Scanner in = new Scanner(System.in);
        String input;

        while (true) {
            showReviewMenu(position);
            input = in.nextLine();

            if (isNotInt(input)) {
                System.out.println("\nIncorrect input.");
                continue;
            }

            int option = Integer.parseInt(input);
            if (option == 0) {
                break;
            }
            if (option < 0) {
                System.out.println("\nIncorrect input.");
                continue;
            }
            if (option > _orders.get(position).getDishesList().size()) {
                System.out.println("\nNo such option.");
                continue;
            }

            for (int i = 0; i < menu.getDishesList().size(); ++i) {
                if (Objects.equals(menu.getDishesList().get(i).getName(),
                        _orders.get(position).getDishesList().get(option - 1).getName())) {
                    makeReview(menu.getDishesList().get(i));
                    break;
                }
            }

            break;
        }
    }

    private void makeReview(Dish dish) {
        final Scanner in = new Scanner(System.in);
        String input;
        int rating = 0;
        String comment = "";

        while (true) {
            System.out.print("\nInput rating (1 - 5): ");
            input = in.nextLine();

            if (isNotInt(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                System.out.print("\nIncorrect input.");
                continue;
            }

            rating = Integer.parseInt(input);
            break;
        }

        System.out.print("\nInput comment: ");
        comment = in.nextLine();

        dish.addReview(new Review(rating, comment));
    }

    private void showReviewMenu(int position) {
        System.out.println("Choose dish to make review (0 to return back):");
        for (int i = 0; i < _orders.get(position).getDishesList().size(); ++i) {
            System.out.println("  " + (i + 1) + ". " + _orders.get(position).getDishesList().get(i));
        }
        System.out.print("\nYour choice: ");
    }

    @Override
    public boolean launchMainMenu(final Menu menu, final ArrayList<User> users) {
        String input;
        final Scanner in = new Scanner(System.in);

        while (true) {
            showConsoleMenu();
            input = in.nextLine();

            switch (input) {
                case "1":
                    makeOrder(menu);
                    break;
                case "2":
                    changeOrder(menu);
                    break;
                case "3":
                    cancelOrder();
                    break;
                case "4":
                    payBill(menu);
                    break;
                case "5":
                    if (_bills != 0) {
                        System.out.println("\nYou can't leave with unpaid bills.");
                        continue;
                    }
                    return false;
                case "6":
                    if (_bills != 0) {
                        System.out.println("\nYou can't leave with unpaid bills.");
                        continue;
                    }
                    return true;
                default:
                    System.out.println("\nIncorrect input.");
            }
        }
    }

    @Override
    public void showConsoleMenu() {
        System.out.println("Choose option:");
        System.out.println("  1. Make an order.");
        System.out.println("  2. Change order.");
        System.out.println("  3. Cancel order.");
        System.out.println("  4. Pay for bills.");
        System.out.println("  5. Log out.");
        System.out.println("  6. Exit system.");
        System.out.print("\nYour choice: ");
    }
}

package UsersLib;

import DishLib.*;
import OrderLib.Menu;

import java.util.Objects;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String login, String password) {
        super(login, password);
    }

    public void manageMenu(final Menu menu) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println(menu);
        System.out.println();

        while (true) {
            System.out.println("Choose option:");
            System.out.println("  1. Add new dish to menu.");
            System.out.println("  2. Remove dish from menu.");
            System.out.println("  3. Change dish.");
            System.out.println("  4. Show current orders.");
            System.out.println("  5. Back.");
            System.out.print("\nYour choice: ");

            input = in.nextLine();
            switch (input) {
                case "1":
                    addDish(menu);
                    break;
                case "2":
                    removeDish(menu);
                    break;
                case "3":
                    changeDish(menu);
                    break;
                case "4":
                    showCurrentOrders();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Incorrect input!\n");
            }
        }
    }

    public void showCurrentOrders() {

    }

    @Override
    public void showConsoleMenu() {
        System.out.println("Choose option:");
        System.out.println("  1. Manage menu.");
        System.out.println("  2. Show current orders.");
        System.out.println("  3. Log out.");
        System.out.println("  4. Exit system.");
        System.out.print("\nYour choice: ");
    }

    private void addDish(final Menu menu) {
        String input;
        DishBuilder db;
        final Scanner in = new Scanner(System.in);

        // Type.
        while (true) {
            System.out.println("Input dish type:");
            System.out.println("  1. First course.");
            System.out.println("  2. Second course.");
            System.out.println("  3. Dessert.");

            input = in.nextLine();
            switch (input) {
                case "1":
                    db = new FirstCourseBuilder();
                    break;
                case "2":
                    db = new SecondCourseBuilder();
                    break;
                case "3":
                    db = new DessertBuilder();
                    break;
                default:
                    System.out.println("\nIncorrect input!");
                    continue;
            }
            break;
        }

        // Name.
        while (true) {
            System.out.print("Input dish name: ");
            input = in.nextLine();
            try {
                for (var dish : menu.getDishesList()) {
                    if (Objects.equals(dish.getName(), input)) {
                        throw new IllegalArgumentException("\nMenu already contains a dish with such name.");
                    }
                }
                db.setName(input);
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
                continue;
            }
            break;
        }

        // Cost.
        while (true) {
            System.out.print("Input dish cost: ");
            input = in.nextLine();
            try {
                db.setCost(Integer.parseInt(input));
            } catch (NumberFormatException ex) {
                System.out.println("\nIncorrect input!");
                continue;
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
            }
            break;
        }

        // Cooking time.
        while (true) {
            System.out.print("Input cooking time: ");
            input = in.nextLine();
            try {
                db.setCookingTime(Integer.parseInt(input));
            } catch (NumberFormatException ex) {
                System.out.println("\nIncorrect input.");
                continue;
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
                continue;
            }
            break;
        }

        // Amount.
        while (true) {
            System.out.print("Input dishes amount: ");
            input = in.nextLine();
            try {
                db.setAmount(Integer.parseInt(input));
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
                continue;
            }
            break;
        }

        menu.getDishesList().add(db.buildPart());
    }

    private void removeDish(final Menu menu) {
        String input;
        final Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Peek a dish to remove: ");
            input = in.nextLine();

            try {
                menu.getDishesList().remove(Integer.parseInt(input) - 1);
            } catch (NumberFormatException ex) {
                System.out.println("\nIncorrect input.");
                continue;
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nIndex must be not out of bounds of the dishes list.");
                continue;
            }
            break;
        }
    }

    private void changeDish(final Menu menu) {
        String input;
        Dish dish;
        final Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Peek a dish to remove: ");
            input = in.nextLine();

            try {
                dish = menu.getDishesList().get(Integer.parseInt(input) - 1);
            } catch (NumberFormatException ex) {
                System.out.println("\nIncorrect input.");
                continue;
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("\nIndex must be not out of bounds of the dishes list.");
                continue;
            }
            break;
        }

        while (true) {
            showChangeDishMenu();
            input = in.nextLine();

            try {
                switch (input) {
                    case "1":
                        changeCost(dish);
                        break;
                    case "2":
                        changeCookingTime(dish);
                        break;
                    case "3":
                        changeAmount(dish);
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("\nIncorrect input!");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
            }
        }
    }

    private void showChangeDishMenu() {
        System.out.println("Choose option:");
        System.out.println("  1. Change cost.");
        System.out.println("  2. Change cooking time.");
        System.out.println("  3. Change dishes amount.");
        System.out.println("  4. Back.");
        System.out.print("\nYour choice: ");
    }

    private void changeCost(Dish dish) {
        String input;
        final Scanner in = new Scanner(System.in);

        System.out.print("Input new dish cost: ");
        input = in.nextLine();

        if (isNotInt(input)) {
            throw new IllegalArgumentException("Incorrect input.");
        }
        if (Integer.parseInt(input) < 0) {
            throw new IllegalArgumentException("Cost can't be negative.");
        }

        dish.setCost(Integer.parseInt(input));
    }

    private void changeCookingTime(Dish dish) {
        String input;
        final Scanner in = new Scanner(System.in);

        System.out.print("Input new dish cooking time: ");
        input = in.nextLine();

        if (isNotInt(input)) {
            throw new IllegalArgumentException("Incorrect input.");
        }
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException("Cooking time can't be negative or zero.");
        }
        if (Integer.parseInt(input) < 5) {
            throw new IllegalArgumentException("Too low. Cooking time should be at least 5 minutes.");
        }
        if (Integer.parseInt(input) > 40) {
            throw new IllegalArgumentException("Too high. Cooking time should be up to 40 minutes.");
        }

        dish.setCookingTime(Integer.parseInt(input));
    }

    private void changeAmount(Dish dish) {
        String input;
        final Scanner in = new Scanner(System.in);

        System.out.print("Input new dish amount: ");
        input = in.nextLine();

        if (isNotInt(input)) {
            throw new IllegalArgumentException("Incorrect input.");
        }
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException("Dishes amount can't be negative or zero.");
        }
        if (Integer.parseInt(input) > 40) {
            throw new IllegalArgumentException("Too high. Dishes amount should be up to 20 minutes.");
        }

        dish.setAmount(Integer.parseInt(input));
    }
}

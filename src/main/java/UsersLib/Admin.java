package UsersLib;

import DishLib.Dish;
import DishLib.FirstCourse;
import OrderLib.Menu;
import OrderLib.OrderStatus;

import java.util.Objects;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String login, String password) {
        super(login, password);
    }

    public void manageMenu(Menu menu) {
        String input = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Choose option:");
            System.out.println("    1. Add new dish to menu.");
            System.out.println("    2. Remove dish from menu.");
            System.out.println("    3. Change dish.");
            System.out.println("    4. Back.");
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
        System.out.println("    1. Manage menu.");
        System.out.println("    2. Show current orders.");
        System.out.println("    3. Log out.");
        System.out.println("    4. Exit system.");
        System.out.print("\nYour choice: ");
    }

    private void addDish(Menu menu) {
        String type, name, input = "";
        int cost, cookingTime;
        Scanner in = new Scanner(System.in);

        // Type.
        while (true) {
            System.out.println("Input dish type:");
            System.out.println("    1. First course.");
            System.out.println("    2. Second course.");
            System.out.println("    3. Dessert.");

            input = in.nextLine();
            switch (input) {
                case "1":
                    type = "1";
                    break;
                case "2":
                    type = "2";
                    break;
                case "3":
                    type = "3";
                    break;
                default:
                    System.out.println("Incorrect input!\n");
                    continue;
            }
            break;
        }

        // Name.
        while (true) {
            System.out.println("Input dish name:");
            input = in.nextLine();
            if (input == null || input.isEmpty()) {
                System.out.println("\nName can't be null or empty.");
                continue;
            }
//            if (menu.getDishesList().contains()) {
//                System.out.println();
//                continue;
//            }

            break;
        }

    }

    private void removeDish(Menu menu) {
    }

    private void changeDish(Menu menu) {

    }
}

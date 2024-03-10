package ValidatorLib;

import UsersLib.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Validator {
    private static final Validator _instance = new Validator();

    private Validator() {}

    public static Validator getInstance() {
        return _instance;
    }

    public User validate(final ArrayList<User> users) {
        final Scanner in = new Scanner(System.in);
        String input;

        while (true) {
            showValidationMenu();
            input = in.nextLine();
            try {
                switch (input) {
                    case "1":
                        return signInAsVisitor(users);
                    case "2":
                        return signUpAsVisitor(users);
                    case "3":
                        return signInAsAdmin(users);
                    case "4":
                        System.exit(0);
                    default:
                        System.out.println("\nIncorrect input.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println('\n' + ex.getMessage());
            }
        }
    }

    private void showValidationMenu() {
        System.out.println("Good morning!\n");
        System.out.println("  1. Sign in as visitor.");
        System.out.println("  2. Sign up as visitor.");
        System.out.println("  3. Sign in as admin.");
        System.out.println("  4. Exit system.");
        System.out.print("\nYour choice:");
    }

    private Visitor signInAsVisitor(ArrayList<User> users) {
        final Scanner in = new Scanner(System.in);
        String input;

        System.out.print("Input login: ");
        User account = findUser(in.nextLine(), users);

        if (account == null) {
            throw new IllegalArgumentException("There is no such users.");
        }
        if (account instanceof Admin) {
            throw new IllegalArgumentException("That login is connected to admin account.");
        }

        System.out.print("Input password: ");
        input = in.nextLine();

        if (Objects.equals(account.getPassword(), input)) {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return (Visitor)account;
    }

    private Visitor signUpAsVisitor(ArrayList<User> users) {
        final Scanner in = new Scanner(System.in);
        String input;
        VisitorBuilder visitorBuilder = new VisitorBuilder();

        System.out.print("Input login: ");
        input = in.nextLine();

        if (findUser(input, users) != null) {
            throw new IllegalArgumentException("That login is unavailable.");
        }

        visitorBuilder.setLogin(input);

        System.out.print("Input password: ");
        input = in.nextLine();

        visitorBuilder.setPassword(input);
        Visitor user = visitorBuilder.buildPart();
        users.add(user);

        return user;
    }

    private Admin signInAsAdmin(ArrayList<User> users) {
        final Scanner in = new Scanner(System.in);
        String input;

        System.out.print("Input admin login: ");
        User account = findUser(in.nextLine(), users);

        if (account == null) {
            throw new IllegalArgumentException("There is no such users.");
        }
        if (account instanceof Visitor) {
            throw new IllegalArgumentException("That login is connected to visitor account.");
        }

        System.out.print("Input password: ");
        input = in.nextLine();

        if (Objects.equals(account.getPassword(), input)) {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return (Admin)account;
    }

    private User findUser(String login, ArrayList<User> users) {
        for (var user : users) {
            if (Objects.equals(user.getLogin(), login)) {
                return user;
            }
        }
        return null;
    }
}

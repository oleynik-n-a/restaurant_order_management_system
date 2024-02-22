package UsersLib;

import OrderLib.Menu;

import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    private final String _password;
    private final String _login;

    public User(String login, String password) {
        _login = login;
        _password = password;
    }

    public String getLogin() {
        return _login;
    }

    public String getPassword() {
        return _password;
    }

    public void logIn() {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input login: ");
            String login = in.nextLine();
            if (Objects.equals(login, _login)) {
                break;
            }
            System.out.println("Nonexistent login!\n");
        }
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input password: ");
            String password = in.nextLine();
            if (Objects.equals(password, _password)) {
                break;
            }
            System.out.println("Incorrect password!\n");
        }
    }

    protected void showMenuItems(Menu menu) {
        System.out.println("Menu:");
        for (int i = 0; i < menu.getDishesList().size(); ++i) {
            System.out.println("  " + (i + 1) + ". " + menu.getDishesList().get(i));
        }
        System.out.println();
    }

    public abstract void showConsoleMenu();
}

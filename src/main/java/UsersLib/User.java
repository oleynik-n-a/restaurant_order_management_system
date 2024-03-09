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

    protected boolean isNotInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return true;
        }
        return false;
    }

    public abstract void showConsoleMenu();
}

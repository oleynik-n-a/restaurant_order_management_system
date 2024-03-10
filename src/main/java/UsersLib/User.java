package UsersLib;

import OrderLib.Menu;
import Program.ManagementSystem;

import java.util.ArrayList;
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

    protected boolean isNotInt(final String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return true;
        }
        return false;
    }

    public abstract void launchMainMenu(final Menu menu,
                                        final ManagementSystem managementSystem,
                                        final ArrayList<User> users);

    protected abstract void showConsoleMenu();
}

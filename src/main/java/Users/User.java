package Users;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    private final String _login;

    public User(String login) {
        _login = login;
    }

    public String getLogin() {
        return _login;
    }

    public void logIn() {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input login: ");
            String login = in.nextLine();
            if (Objects.equals(login, _login)) {
                break;
            }
            System.out.println("Nonexistent login!");
        }
    }
}

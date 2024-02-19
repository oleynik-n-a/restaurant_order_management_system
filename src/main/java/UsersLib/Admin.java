package UsersLib;

import java.util.Objects;
import java.util.Scanner;

public class Admin extends User {
    private final String _password;

    public Admin(String login, String password) {
        super(login);
        _password = password;
    }

    public String getPassword() {
        return _password;
    }

    @Override
    public void logIn() {
        super.logIn();
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Input password: ");
            String password = in.nextLine();
            if (Objects.equals(password, _password)) {
                break;
            }
            System.out.println("Incorrect password!");
        }
    }
}

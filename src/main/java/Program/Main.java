package Program;

import OrderLib.Kitchen;
import OrderLib.Menu;
import UsersLib.User;
import UsersLib.UserBuilder;
import ValidatorLib.Validator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Serializer<Menu> menuSerializer = new Serializer<>("src/main/java/DataBase/menu.data");

        Serializer<ArrayList<User>> usersSerializer = new Serializer<>("src/main/java/DataBase/users.data");


        Menu menu = menuSerializer.deserialize();
        Kitchen kitchen;
        ArrayList<User> users = usersSerializer.deserialize();

        while (true) {
            User user = Validator.getInstance().validate(users);
            if (user == null) {
                continue;
            }
            user.launchMainMenu(menu, users);
        }
    }
}

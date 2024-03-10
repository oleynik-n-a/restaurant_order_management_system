package Program;

import OrderLib.Kitchen;
import OrderLib.Menu;
import UsersLib.User;
import ValidatorLib.Validator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Serializer<Menu> menuSerializer = new Serializer<>("src/main/java/DataBase/menu.data");
        Serializer<Kitchen> kitchenSerializer = new Serializer<>("src/main/java/DataBase/kitchen.data");
        Serializer<ArrayList<User>> usersSerializer = new Serializer<>("src/main/java/DataBase/users.data");

        Menu menu = menuSerializer.deserialize();
        Kitchen kitchen = kitchenSerializer.deserialize();
        ArrayList<User> users = usersSerializer.deserialize();

        while (true) {
            User user = Validator.getInstance().validate(users);
            if (user == null) {
                continue;
            }
            user.launchMainMenu(menu, users);
            break;
        }

        menuSerializer.serialize(menu);
        kitchenSerializer.serialize(kitchen);
        usersSerializer.serialize(users);
    }
}

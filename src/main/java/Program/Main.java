package Program;

import DishLib.Dessert;
import DishLib.Dish;
import DishLib.FirstCourse;
import DishLib.SecondCourse;
import OrderLib.Kitchen;
import OrderLib.Menu;
import UsersLib.Admin;
import UsersLib.User;
import UsersLib.Visitor;
import ValidatorLib.Validator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Serializer<Menu> menuSerializer = new Serializer<>("src/main/java/DataBase/menu.data");
        Serializer<Kitchen> kitchenSerializer = new Serializer<>("src/main/java/DataBase/kitchen.data");
        Serializer<ArrayList<User>> usersSerializer = new Serializer<>("src/main/java/DataBase/users.data");

        Menu menu;
        Kitchen kitchen = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            menu = menuSerializer.deserialize();
            kitchen = kitchenSerializer.deserialize();
            users = usersSerializer.deserialize();
        } catch (IOException ex) {
            ArrayList<Dish> dishes = new ArrayList<>();
            dishes.add(new Dessert("ChocoPie", 10, 5, 12));
            dishes.add(new Dessert("Yashkino", 5, 3, 20));
            dishes.add(new FirstCourse("Soup", 200, 30, 3));
            dishes.add(new SecondCourse("Pasta", 150, 15, 10));
            menu = new Menu(dishes);
            users.add(new Admin("Nikita", "1234"));
            users.add(new Visitor("Bogdan", "1234"));
            users.add(new Visitor("Artem", "1234"));

            menuSerializer.serialize(menu);
            kitchenSerializer.serialize(kitchen);
            usersSerializer.serialize(users);
        }

        while (true) {
            User user = Validator.getInstance().validate(users);
            if (user == null) {
                break;
            }
            boolean f = user.launchMainMenu(menu, users);
            if (f) {
                break;
            }
        }

        menuSerializer.serialize(menu);
        kitchenSerializer.serialize(kitchen);
        usersSerializer.serialize(users);
    }
}

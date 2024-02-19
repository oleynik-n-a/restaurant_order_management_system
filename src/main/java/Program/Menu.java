package Program;

import DishLib.Dish;

import java.io.*;
import java.util.List;

public class Menu {
    private final List<Dish> _dishesList;
    private final Serializer<List<Dish>> _serializer;

    public Menu() throws IOException, ClassNotFoundException {
        _serializer = new Serializer<List<Dish>>("../Database/");
        _dishesList = _serializer.deserialize();
    }

    public List<Dish> getDishesList() {
        return _dishesList;
    }

    public void saveData() throws IOException, ClassNotFoundException {
        _serializer.serialize(_dishesList);
    }
}

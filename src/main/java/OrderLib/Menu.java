package OrderLib;

import DishLib.Dish;
import Program.Serializer;

import java.io.*;
import java.util.ArrayList;

public class Menu {
    private final ArrayList<Dish> _dishesList;
    private final Serializer<ArrayList<Dish>> _serializer;

    public Menu() throws IOException, ClassNotFoundException {
        _serializer = new Serializer<ArrayList<Dish>>("../Database/");
        _dishesList = _serializer.deserialize();
    }

    public ArrayList<Dish> getDishesList() {
        return _dishesList;
    }

    public void saveData() throws IOException {
        _serializer.serialize(_dishesList);
    }
}

package OrderLib;

import DishLib.Dish;
import Program.Serializer;

import java.io.*;
import java.util.ArrayList;

public class Menu {
    private final ArrayList<Dish> _dishesList;

    public Menu(ArrayList<Dish> dishesList) {
        _dishesList = dishesList;
    }

    public ArrayList<Dish> getDishesList() {
        return _dishesList;
    }
}

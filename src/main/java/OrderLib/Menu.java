package OrderLib;

import DishLib.Dish;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
    private final ArrayList<Dish> _dishesList;

    public Menu(ArrayList<Dish> dishesList) {
        _dishesList = dishesList;
    }

    public ArrayList<Dish> getDishesList() {
        return _dishesList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Menu:\n");
        for (int i = 0; i < _dishesList.size(); ++i) {
            str.append("  ").append(i + 1).append(". ").append(_dishesList.get(i).toString()).append('\n');
        }
        return str.toString();
    }
}

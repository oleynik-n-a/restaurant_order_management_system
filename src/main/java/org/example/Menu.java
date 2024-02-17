package org.example;

import DishLib.Dish;

import java.io.*;
import java.util.List;

public class Menu {
    private final List<Dish> dishesList;

    public Menu() throws IOException, ClassNotFoundException {
        Serializer<List<Dish>> serializer = new Serializer<List<Dish>>("../Database/");
        dishesList = serializer.Deserialize();
    }

    public List<Dish> getDishesList() {
        return dishesList;
    }
}

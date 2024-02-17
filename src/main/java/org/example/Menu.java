package org.example;

import DishLib.Dish;

import java.io.*;
import java.util.List;

public class Menu {
    private final List<Dish> dishesList;
    private final Serializer<List<Dish>> serializer;

    public Menu() throws IOException, ClassNotFoundException {
        serializer = new Serializer<List<Dish>>("../Database/");
        dishesList = serializer.Deserialize();
    }

    public List<Dish> getDishesList() {
        return dishesList;
    }

    public void SaveData() throws IOException, ClassNotFoundException {
        serializer.Serialize(dishesList);
    }
}

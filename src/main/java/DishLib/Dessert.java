package DishLib;

import java.io.Serializable;

public class Dessert extends Dish implements Serializable {
    public Dessert(String name, int cost, int cookingTime, int amount) {
        super(name, cost, cookingTime, amount);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: dessert.";
    }
}

package DishLib;

public class Dessert extends Dish {
    public Dessert(String name, int cost, int cookingTime, int amount) {
        super(name, cost, cookingTime, amount);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: dessert.";
    }
}

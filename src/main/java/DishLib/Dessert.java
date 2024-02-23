package DishLib;

public class Dessert extends Dish {
    public Dessert(String name, int cost, int cookingTime) {
        super(name, cost, cookingTime);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: dessert.";
    }
}

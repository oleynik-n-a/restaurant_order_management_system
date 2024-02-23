package DishLib;

import BuilderLib.IBuilder;

public abstract class DishBuilder implements IBuilder<Dish> {
    protected String _name;
    protected int _cost;
    protected int _cookingTime;

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty.");
        }
        _name = name;
    }

    public void setCost(int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Cost can't be negative or zero.");
        }
        _cost = cost;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime <= 0) {
            throw new IllegalArgumentException("Cooking time can't be negative or zero.");
        }
        if (cookingTime < 5) {
            throw new IllegalArgumentException("Too low. Cooking time should be at least 5 minutes.");
        }
        if (cookingTime > 40) {
            throw new IllegalArgumentException("Too high. Cooking time should be up to 40 minutes.");
        }
        _cookingTime = cookingTime;
    }

    public abstract Dish buildPart();
}

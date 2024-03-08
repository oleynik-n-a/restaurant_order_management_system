package DishLib;

import BuilderLib.IBuilder;

public abstract class DishBuilder implements IBuilder<Dish> {
    protected String _name;
    protected int _cost;
    protected int _cookingTime;
    protected int _amount;

    public void setName(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty.");
        }
        _name = value;
    }

    public void setCost(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cost can't be negative.");
        }
        _cost = value;
    }

    public void setCookingTime(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Cooking time can't be negative or zero.");
        }
        if (value < 5) {
            throw new IllegalArgumentException("Too low. Cooking time should be at least 5 minutes.");
        }
        if (value > 40) {
            throw new IllegalArgumentException("Too high. Cooking time should be up to 40 minutes.");
        }
        _cookingTime = value;
    }

    public void setAmount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Dishes amount can't be negative or zero.");
        }
        if (value > 40) {
            throw new IllegalArgumentException("Too high. Dishes amount should be up to 20 minutes.");
        }
        _amount = value;
    }

    public abstract Dish buildPart();
}

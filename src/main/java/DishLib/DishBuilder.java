package DishLib;

import BuilderLib.IBuilder;

public abstract class DishBuilder implements IBuilder<Dish> {
    protected String _name;
    protected int _cost;
    protected int _cookingTime;

    public void setName(String name) {
        _name = name;
    }

    public void setCost(int cost) {
        _cost = cost;
    }

    public void setCookingTime(int cookingTime) {
        _cookingTime = cookingTime;
    }

    public abstract Dish buildPart();
}

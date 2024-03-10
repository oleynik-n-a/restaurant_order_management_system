package DishLib;

public class DessertBuilder extends DishBuilder {
    @Override
    public Dessert buildPart() {
        return new Dessert(_name, _cost, _cookingTime, _amount);
    }
}

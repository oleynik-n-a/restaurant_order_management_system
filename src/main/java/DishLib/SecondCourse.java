package DishLib;

public class SecondCourse extends Dish {
    public SecondCourse(String name, int cost, int cookingTime, int amount) {
        super(name, cost, cookingTime, amount);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: second course.";
    }
}

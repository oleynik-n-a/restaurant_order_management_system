package DishLib;

import java.io.Serializable;

public class FirstCourse extends Dish implements Serializable {
    public FirstCourse(String name, int cost, int cookingTime, int amount) {
        super(name, cost, cookingTime, amount);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: first course.";
    }
}

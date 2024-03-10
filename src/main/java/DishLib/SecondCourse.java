package DishLib;

import java.io.Serializable;

public class SecondCourse extends Dish implements Serializable {
    public SecondCourse(String name, int cost, int cookingTime, int amount) {
        super(name, cost, cookingTime, amount);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: second course.";
    }
}

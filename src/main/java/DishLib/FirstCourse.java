package DishLib;

public class FirstCourse extends Dish {
    public FirstCourse(String name, int cost, int cookingTime) {
        super(name, cost, cookingTime);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: first course.";
    }
}

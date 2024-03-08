package DishLib;

public class SecondCourse extends Dish {
    public SecondCourse(String name, int cost, int cookingTime) {
        super(name, cost, cookingTime);
    }

    @Override
    public String toString() {
        return super.toString() + ", type: second course.";
    }
}

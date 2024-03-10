package DishLib;

public class FirstCourseBuilder extends DishBuilder {
    @Override
    public FirstCourse buildPart() {
        return new FirstCourse(_name, _cost, _cookingTime, _amount);
    }
}

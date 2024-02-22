package DishLib;

public class SecondCourseBuilder extends DishBuilder {
    @Override
    public SecondCourse buildPart() {
        return new SecondCourse(_name, _cost, _cookingTime);
    }
}

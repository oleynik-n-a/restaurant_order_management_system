package DishLib;

import java.util.ArrayList;

public abstract class Dish implements IReviewable {
    private final String _name;
    private int _cost;
    private int _cookingTime;
    private int _amount;
    private final ArrayList<Review> _reviews;

    public Dish(String name, int cost, int cookingTime, int amount) {
        _name = name;
        _cost = cost;
        _cookingTime = cookingTime;
        _amount = amount;
        _reviews = new ArrayList<Review>();
    }

    public String getName() {
        return _name;
    }

    public int getCost() {
        return _cost;
    }

    public void setCost(int value) {
        _cost = value;
    }

    public int getCookingTime() {
        return _cookingTime;
    }

    public void setCookingTime(int value) {
        _cookingTime = value;
    }

    public int getAmount() {
        return _amount;
    }

    public void setAmount(int value) {
        _amount = value;
    }

    public String[] getReviews() {
        String[] revs = new String[_reviews.size()];
        for (int i = 0; i < _reviews.size(); ++i) {
            revs[i] = _reviews.get(i).toString();
        }
        return revs;
    }

    public void addReview(Review rev) {
        _reviews.add(rev);
    }

    public double getRating() {
        if (_reviews.isEmpty()) {
            return 0;
        }
        double rating = 0.0;
        for (var review : _reviews) {
            rating += review.getRating();
        }
        return rating / _reviews.size();
    }

    @Override
    public String toString() {
        return "name: " + _name + ", cost: " + _cost + ", cooking time: " + _cookingTime;
    }
}

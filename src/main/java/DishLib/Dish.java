package DishLib;

import java.util.ArrayList;
import java.util.List;

public abstract class Dish implements IReviewable {
    private final String name;
    private int cost;
    private int cookingTime;
    private final List<Review> reviews;

    public Dish(String name, int cost, int cookingTime) {
        this.name = name;
        this.cost = cost;
        this.cookingTime = cookingTime;
        reviews = new ArrayList<Review>();
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int value) {
        cost = value;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int value) {
        cookingTime = value;
    }

    public String[] getReviews() {
        String[] revs = new String[reviews.size()];
        for (int i = 0; i < reviews.size(); ++i) {
            revs[i] = reviews.get(i).toString();
        }
        return revs;
    }

    public void addReview(Review rev) {
        reviews.add(rev);
    }

    public double getRating() {
        double rating = 0.0;
        for (var review : reviews) {
            rating += review.getRating();
        }
        return rating / reviews.size();
    }
}

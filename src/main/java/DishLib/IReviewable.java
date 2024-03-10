package DishLib;

public interface IReviewable {
    String[] getReviews();

    void addReview(Review rev);

    double getRating();
}

package DishLib;

public interface IReviewable {
    public String[] getReviews();

    public void addReview(Review rev);

    public double getRating();
}

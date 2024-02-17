package DishLib;

public class Review {
    private final int rating;
    private final String commentary;

    public Review(int rating, String commentary) {
        this.rating = rating;
        this.commentary = commentary;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", rating, commentary);
    }
}

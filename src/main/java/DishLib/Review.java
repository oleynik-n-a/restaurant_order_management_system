package DishLib;

public final class Review {
    private final int _rating;
    private final String _commentary;

    public Review(int rating, String commentary) {
        _rating = rating;
        _commentary = commentary;
    }

    public int getRating() {
        return _rating;
    }

    @Override
    public String toString() {
        return String.format(_rating + ". " + _commentary);
    }
}

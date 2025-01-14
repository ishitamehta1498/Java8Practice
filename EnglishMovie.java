public class EnglishMovie {
    private String name;
    private double rating;
    private int release;

    public EnglishMovie(String name, double rating, int release) {
        this.name = name;
        this.rating = rating;
        this.release = release;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "EnglishMovie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", release=" + release +
                '}';
    }
}

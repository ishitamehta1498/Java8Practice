public class Movie implements Comparable<Movie>{
    private String name;
    private double rating;
    private int release;

    public Movie(String name, double rating, int release) {
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
    public int compareTo(Movie m) {
        return this.release-m.release;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", release=" + release +
                '}';
    }
}

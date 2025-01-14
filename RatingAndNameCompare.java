import java.util.Comparator;

public class RatingAndNameCompare implements Comparator<EnglishMovie> {
    @Override
    public int compare(EnglishMovie o1, EnglishMovie o2) {
        int nameCompare=o1.getName().compareTo(o2.getName());
        int ratingCompare=o1.getRating()<o2.getRating()?-1:o1.getRating()>o2.getRating()?1:0;
        return ratingCompare==0?nameCompare:ratingCompare;
    }
}

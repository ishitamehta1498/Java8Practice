import java.util.Comparator;

public class RatingCompare implements Comparator<EnglishMovie> {
//asc
//    @Override
//    public int compare(EnglishMovie m1, EnglishMovie m2) {
//        if(m1.getRating()>m2.getRating())
//            return 1;
//        else if (m1.getRating()<m2.getRating())
//            return -1;
//        else return 0;
//    }

    //desc
    @Override
    public int compare(EnglishMovie m1, EnglishMovie m2) {
        if(m1.getRating()>m2.getRating())
            return -1;
        else if (m1.getRating()<m2.getRating())
            return 1;
        else return 0;
    }
}

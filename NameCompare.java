import java.util.Comparator;

public class NameCompare implements Comparator<EnglishMovie> {
    public int compare(EnglishMovie e1,EnglishMovie e2){
        return e1.getName().compareTo(e2.getName());
    }
}

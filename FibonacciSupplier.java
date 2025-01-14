import java.util.function.Supplier;

public class FibonacciSupplier implements Supplier<Integer> {
    int previous=0;
    int current=1;
    boolean firstCall=true;

    @Override
    public Integer get() {
        if(firstCall){
            firstCall=false;
            return previous;
        }
        int next=previous+current;
        previous=current;
        current=next;
        return previous;
    }
}

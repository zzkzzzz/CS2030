// For memoization

import java.util.Objects;
import java.util.function.Supplier;
import java.util.TreeMap;


public class Memo<T> {

    private static TreeMap<String, Integer> getTable = new TreeMap<>();

    public static void printGetTable() {
        System.out.println("Memo Get Table:");        
        getTable.forEach((k,v)->
                          System.out.println(
                                             String.format("%s: count-> %d", k, v)));
            }

    private boolean hasBeenRun;
    private T value;
    private Supplier<T> supplier;

    private Memo(Supplier<T> s) {
        this.hasBeenRun = false;
        this.value = null;
        this.supplier = s;
    }

    public static <T> Memo<T>  make(Supplier<T> s) {
        return new Memo<>(Objects.requireNonNull(s));
    }

    public T get() {
        if (!hasBeenRun) {
            this.hasBeenRun = true;
            this.value = this.supplier.get();
        }
        getTable.merge(this.value.toString(), 1, (x,y)->x+1 );
        return this.value;
    }
}

package cs2030.simulator;

public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Pair Constructor.
     * 
     * @param first  first element
     * @param second second element
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * return a Pair value describing the given value.
     * 
     * @param <T> the type of first value
     * @param <U> the type of second value
     * @param t   first value
     * @param u   second value
     * @return Pair value describing the given value
     */
    public static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<>(t, u);
    }

    /**
     * get the first element.
     * 
     * @return first element
     */
    public T first() {
        return first;
    }

    /**
     * get the second element.
     * 
     * @return second element
     */
    public U second() {
        return second;
    }
}

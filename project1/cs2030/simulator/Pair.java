package cs2030.simulator;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public Pair<T, U> of(T t, U u) {
        return new Pair<>(t, u);
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }
}

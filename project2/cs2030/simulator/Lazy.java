package cs2030.simulator;

import java.util.function.Supplier;
import java.util.NoSuchElementException;
import java.util.Optional;

class Lazy<T extends Comparable<T>> {

    private Optional<T> value;
    private final Supplier<T> supplier;

    private Lazy(Supplier<T> supplier) {
        this.value = Optional.empty();
        this.supplier = supplier;
    }

    public static <T extends Comparable<T>> Lazy<T> of(T v) {
        // convert the value to a supplier
        return new Lazy<>(() -> v);
    }

    public static <T extends Comparable<T>> Lazy<T> of(Supplier<T> supplier) {
        try {
            // check null
            Optional<Supplier<T>> check = Optional.of(supplier);
            check.orElseThrow();
            return new Lazy<T>(supplier);
        } catch (Exception e) {
            throw new NoSuchElementException("No value present");
        }
    }

    public T get() {
        // if the value is available， get the value
        // if not, get the result of the supplier
        T v = this.value.orElseGet(supplier);
        this.value = Optional.of(v);
        return v;
    }

    @Override
    public String toString() {
        // map the value to String first then check available or not
        return this.value.map(x -> x + "").orElseGet(() -> "?");
    }
}
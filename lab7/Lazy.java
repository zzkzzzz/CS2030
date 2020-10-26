import java.util.function.Supplier;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
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
        return new Lazy<T>(() -> v);
    }

    public static <T extends Comparable<T>> Lazy<T> of(Supplier<T> supplier) {
        try {
            Optional<Supplier<T>> check = Optional.of(supplier);
            check.orElseThrow();
            return new Lazy<T>(supplier);
        } catch (Exception e) {
            throw new NoSuchElementException("No value present");
        }
    }

    public T get() {
        T v = this.value.orElseGet(supplier);
        this.value = Optional.of(v);
        return v;
    }

    public <R extends Comparable<R>> Lazy<R> map(Function<T, R> f) {
        Supplier<R> newSupplier = () -> f.apply(this.value.orElseGet(supplier));
        return new Lazy<>(newSupplier);
    }

    public <R extends Comparable<R>> Lazy<R> flatMap(Function<T, Lazy<R>> f) {
        return f.apply(this.value.orElseGet(supplier));
    }

    public <U extends Comparable<U>, R extends Comparable<R>> Lazy<R> combine(Lazy<U> other,
            BiFunction<T, U, R> combiner) {
        return this.flatMap(x -> other.map(y -> combiner.apply(x, y)));
    }

    public Lazy<Boolean> test(Predicate<T> pred) {
        return new Lazy<>(() -> pred.test(this.value.orElseGet(this.supplier)));
    }

    public Lazy<Integer> compareTo(Lazy<T> other) {
        return this.flatMap(x -> other.map(y -> x.compareTo(y)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Lazy) {
            Lazy<?> other = (Lazy<?>) obj;
            return this.get().equals(other.get());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.value.map(x -> x + "").orElseGet(() -> "?");
    }
}
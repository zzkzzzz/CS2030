import java.util.function.Supplier;

public class Lazy<T> {

    private final Supplier<T> value;

    public Lazy(Supplier<T> v) {
        this.value = v;
    }

    public T get() {
        return this.value.get();
    }
}

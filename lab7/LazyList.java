import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class LazyList<T extends Comparable<T>> {
    private final List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    /********
     * Generate a lazy list as
     * [Lazy.of(seed),Lazy.of(seed).map(f),Lazy.of(seed).map(f).map(f)....] up to n
     * elements. The values will not be evaluated.
     */
    static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<>(Stream.iterate(Lazy.of(seed), x -> x.map(f)).limit(n).collect(Collectors.toList()));
    }

    public T get(int i) {
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        return this.list.indexOf(Lazy.of(v));
    }

}
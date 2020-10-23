import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class LazyList<T extends Comparable<T>> {
    private List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<>(Stream.iterate(Lazy.of(seed), x -> x.map(f)).limit(n).collect(Collectors.toList()));
    }

    public T get(int i) {
        System.out.println(list);
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        System.out.println(list);
        return this.list.indexOf(Lazy.of(v));
    }

}
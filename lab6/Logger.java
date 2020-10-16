import java.util.function.Function;
import java.util.function.Predicate;

public interface Logger<T> {
    
    public static <T> Logger<T> make(T thing) {
        if (thing instanceof Logger)
            throw new IllegalArgumentException("already a Logger");
        if (thing == null)
            throw new IllegalArgumentException("argument cannot be null");
        return new LoggerImpl<T>(thing);
    }

    public void printlog();

    public boolean test(Predicate<? super T> predicate);

    public <U> LoggerImpl<U> map(Function<? super T, ? extends U> f);

    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> f);

}

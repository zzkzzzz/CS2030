//*************** Sandbox.java **********
/**
   Functor that separates exception handling from main processing.
 */
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Objects;

/** Class Sandbox is a Functor, and is an immutable class.
    It's purpose is to separate exception handling from the main
    sequence of mapping.
 */
public final class Sandbox<T> {
    private final T thing;
    private final Exception exception;

    private Sandbox(T thing, Exception ex) {
        this.thing = thing;
        this.exception = ex;
    }
    
    public static <T> Sandbox<T> make(T thing) {
        return new Sandbox<T>(Objects.requireNonNull(thing), null);
    }

    public static <T> Sandbox<T> makeEmpty() {
        return new Sandbox<T>(null, null);
    }

    public boolean isEmpty() {
        return this.thing==null;
    }

    public boolean hasNoException() {
        return this.exception == null;
    }

    public <U> Sandbox<U> map(Function<T,U> f) {
        return map(f, "");
    }
        
    public <U> Sandbox<U> map(Function<T,U> f, String errorMessage) {
        if (this.isEmpty())
            return new Sandbox<U>(null, this.exception);
        
        try {
            return new Sandbox<U>(f.apply(this.thing), null);
        }
        catch (Exception ex) {
            return new Sandbox<U>(null,
                                  new Exception(errorMessage, ex));
        }
    }

    /** consume calls the Consumer argument with the thing.
        Exceptions, if any, are handled here.
    */
    public void consume(Consumer<T> eat) {
        if (this.isEmpty() && !this.hasNoException())
            handleException();

        if (!this.isEmpty() && this.hasNoException()) 
            eat.accept(this.thing);
    }

    private void handleException() {
        System.err.printf(this.exception.getMessage());
        Throwable t = this.exception.getCause();
        if (t != null) {
            String msg = t.getMessage();
            if (msg != null)
                System.err.printf(": %s", msg);
        }
        System.err.println("");
    }
}



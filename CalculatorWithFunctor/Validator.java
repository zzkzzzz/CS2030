//Validator.java
/**
   Monad that separates exception handling from main processing.

 */
package calcm;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Objects;

/** Class Validator is a Functor, and is an immutable class.
    It's purpose is to separate exception handling from the main
    sequence of mapping.
 */
public final class Validator<T> {

    private final T thing;
    private final Exception exception;

    private Validator(T thing, Exception ex) {
        this.thing = thing;
        this.exception = ex;
    }
    
    public static <T> Validator<T> make(T thing) {
        return new Validator<T>(Objects.requireNonNull(thing), null);
    }

    public static <T> Validator<T> makeEmpty() {
        return new Validator<T>(null, null);
    }

    public boolean isEmpty() {
        return this.thing==null;
    }

    public boolean hasNoException() {
        return this.exception == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Validator) {
            Validator other = (Validator) obj;
            return this.thing == other.thing &&
                this.exception == other.exception;
        }
        return false;
    }

    public <U> Validator<U> map(Function<T,U> f) {
        return map(f, "");
    }
        
    public <U> Validator<U> map(Function<T,U> f, String errorMessage) {
        if (isEmpty())
            return new Validator<U>(null, this.exception);
        
        try {
            return new Validator<U>(f.apply(thing), null);
        }
        catch (Exception ex) {
            return new Validator<U>(null,
                                    new Exception(errorMessage, ex));
        }
    }

    /** consume calls the Consumer argument with the thing.
        Exceptions, if any, are handled here.
    */

    public void consume(Consumer<T> eat) {
        if (isEmpty() && hasNoException()) 
            return;

        if (isEmpty() && !hasNoException())
            handleException();

        if (!isEmpty() && hasNoException()) 
            eat.accept(thing); //eating may throw exception
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



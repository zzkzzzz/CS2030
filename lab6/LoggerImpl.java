import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

public class LoggerImpl<T> implements Logger<T> {
    private final T thing;
    private final List<String> logList;

    public LoggerImpl(T thing) {
        this.thing = thing;
        this.logList = new ArrayList<>();
        this.logList.add(String.format("Value initialized. Value = %s", thing.toString()));
    }

    public LoggerImpl(T thing, List<String> logList) {
        this.thing = thing;
        this.logList = new ArrayList<>(logList);
    }

    public T getThing() {
        return this.thing;
    }

    public List<String> getLogList() {
        return this.logList;
    }

    /**
     * If a value is present, returns an LoggerImpl describing the result of
     * applying the given mapping function to the value, otherwise returns an empty
     * LoggerImpl.
     * 
     * @param f the mapping function to apply to a value
     * 
     */
    public <U> LoggerImpl<U> map(Function<? super T, ? extends U> f) {
        if (this.isEmpty())
            return new LoggerImpl<U>(null);

        List<String> newLogList = new ArrayList<>(this.logList);
        U newThing = f.apply(this.thing);

        // When value remains unchanged
        if (newThing.equals(this.thing))
            newLogList.add(String.format("Value unchanged. Value = %s", newThing.toString()));
        // When value is modified
        else
            newLogList.add(String.format("Value changed! New value = %s", newThing.toString()));

        return new LoggerImpl<U>(newThing, newLogList);
    }

    /**
     * If a value is present, returns an LoggerImpl describing the result of
     * applying the given mapping function to the value, otherwise returns an empty
     * LoggerImpl.
     * 
     * Unwrapping the parameter from the LoggerImpl context.
     * 
     * @param f the mapping function to apply to a value
     * 
     */
    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> f) {
        if (this.isEmpty())
            return new LoggerImpl<U>(null);

        List<String> prevLogList = new ArrayList<String>(this.logList);

        LoggerImpl<? extends U> temp = (LoggerImpl<? extends U>) f.apply(this.thing);
        List<String> tempLogList = temp.getLogList();

        // remove the first log msg of temLogList
        // join two log list together
        // bcs first msg of temLogList is equal to last msg of prevLogList
        tempLogList.remove(0);
        List<String> newLogList = Stream.concat(prevLogList.stream(), tempLogList.stream())
                .collect(Collectors.toList());

        return new LoggerImpl<U>(temp.getThing(), newLogList);
    }

    public boolean test(Predicate<? super T> predicate) {
        return predicate.test(this.thing);
    }

    public void printlog() {
        List<String> newLogList = new ArrayList<String>(this.logList);
        for (String msg : newLogList) {
            System.out.println(msg);
        }
    }

    public boolean isEmpty() {
        return this.thing == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof LoggerImpl) {
            // unknown type of LoggerImpl
            LoggerImpl<?> logger = (LoggerImpl<?>) obj;
            // check the value and the number of log messages
            return this.thing.equals(logger.getThing()) && this.logList.size() == logger.getLogList().size();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Logger[%s]", this.thing.toString());
    }

}

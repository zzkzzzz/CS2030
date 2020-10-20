// LazyList

import java.util.function.Supplier;
import java.util.function.Consumer;
import java.lang.IllegalStateException;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.Scanner;


/** ******************
    The LazyList abstraction.
    To compile this file, use 'jpp Lazylist.java'
    This will preprocess the macros, and invoke javacc
 */
public class LazyList<T> {

    /* A LL consists of a head of type T,
       whose tail is a Thunk of a LazyList<T>
       The flag amIEmpty indicates if this list is empty.
      */
    private final T head;
    private final Thunk(LazyList<T>) tail;
    private final boolean amIEmpty;
    
    /** **************
        Main list constructor. But users should use the macro:
        LLmake(a, b), which is syntactic sugar for:
        new LazyList(a, freeze(b))

        For normal case (ie. without memoization):
        freeze(x) is syntactic sugar for: (()->(x))
        Thunk(T) is syntactic sugar for: Supplier<T>

        If memoization is used, then
        freeze(x) is syntactic sugar for: new Memo.make( ()->(x) )
        Thunk(T) is syntactic sugar for Memo<T>
    */
    public LazyList(T a, Thunk(LazyList<T>) b) {
        this.head = a;
        this.tail = b;
        this.amIEmpty = false;
    }

    /** **************
        Private constructor of an empty list.
    */
    private LazyList() {
        this.head = null;
        this.tail = null;
        this.amIEmpty = true;

    }

    /** **************
        Convenience function to thaw a thunk.
    */
    public static <T> T thaw(Thunk(T) ice) {
        return ice.get();
    }
    
    /** **************
        Create an empty LazyList.
    */
    public static <T> LazyList<T> makeEmpty() {
        return new LazyList<T>();
    }
    
    /** **************
        Return the head of this list.
    */
    public T head() {
        if (this.isEmpty())
            throw new IllegalArgumentException("head() called on empty list!");
        
        return this.head;
    }

    /** **************
        Thaw the tail of the list, and return it.
    */
    public LazyList<T> tail() {
        if (this.isEmpty())
            throw new IllegalArgumentException("tail() called on empty list!");
        return thaw(this.tail);
    }

    /** **************
        Return true of this list is empty, false otherwise.
    */
    public boolean isEmpty() {
        return this.amIEmpty;
    }

    /** **************
        Return a human-readable string of this list.
    */
    @Override
    public String toString() {
        Scanner sc = new Scanner(this.tail.toString()).useDelimiter("Lambda");
        String s1 = sc.next();
        String s2 = sc.next();        
        return String.format("head: %s\ntail: thunk%s",
                             this.head.toString(),s2);
    }

    /** **************
        Print all the elements in this list. This thaws all the
        elements. If this list is infinite, then the printing
        could go on forever.
    */
    public void print() {
        LazyList<T> me = this;
        System.out.printf("(* ");
        while (!me.isEmpty()) {
            System.out.printf("%s, ", me.head());
            me = me.tail();
        }
        System.out.println("*)");
    }

    /** **************
        Convenience function to make a LL of multiple arguments.
    */
    @SafeVarargs
    public static <T> LazyList<T> fromList(T ... items) {
        List<T> list = Arrays.asList(items);
        return helper(list);
    }

    private static <T> LazyList<T> helper(List<T> list) {
        if (list.isEmpty())
            return LazyList.makeEmpty();
        else
            return LLmake(list.get(0),
                          helper(list.subList(1,list.size())));
    }

    /** **************
        Apply the mapping function f onto each element of this list,
        and return a new LL containing the mapped elements.
    */
    public <R> LazyList<R> map(Function<T,R> f) {
        if (this.isEmpty())
            return LazyList.makeEmpty();
        else
            return LLmake(f.apply(this.head()),
                          this.tail().map(f));
    }

    /** **************
        Apply the mapping function f onto each element of this list, and
        return a new LL containing all the flattened mapped elements.
        Note that f produces a list for each element. But the returned
        LL flattens them all, ie. removes nested lists.
    */
    public <R> LazyList<R> flatmap(Function<T, LazyList<R>> f) {
        // Replace the next line with your own code.
            return LazyList.makeEmpty();
    
    }

    
    /** **************
        Return a new LL whose elements (from this list)
        pass the test of the predicate pred.
    */
    public LazyList<T> filter(Predicate<T> pred) {
        if (this.isEmpty())
            return LazyList.makeEmpty();
        else if (pred.test(this.head()))
            return new LazyList<>(this.head(),
                                  freeze(this.tail().filter(pred)));
        else
            return this.tail().filter(pred);
    }

    /** **************
        Return a new LL of length maxSize.
        The new list contains the same elements as this one.
    */
    public LazyList<T> limit(long maxSize) {
        if (maxSize == 0)
            return LazyList.makeEmpty();
        else
            return LLmake(this.head(),
                          this.tail().limit(maxSize - 1));
    }


    /** **************
        Return the element at position given by idx.
        idx starts from 0, and should be non-negative.
    */
    public T get(int idx) {
        if (this.isEmpty() || idx < 0)
            return null;
        else if (idx == 0)
            return this.head();
        else
            return this.tail().get(idx - 1);
    }

    /** **************
        Convenience function: return a LazyList<Integer> of integers
        from a (inclusive) to b (exclusive)
    */
    public static LazyList<Integer> intRange(int a, int b) {
        if (a >= b)
            return LazyList.makeEmpty();
        else
            return LLmake(a, intRange(a + 1, b));
    }

    /** **************
        Concatenate other list to this one.
    */
    public LazyList<T> concat(LazyList<T> other) {
        if (this.isEmpty())
            return other;
        else
            return LLmake(this.head(),
                          this.tail().concat(other));
    }

    /** **************
        Return a new list containing the same elements as this list,
        but in reverse order.
    */
    public LazyList<T> reverse() {
        // Replace the next line with your own code.
        return this;
    }

    /** **************
        General way to combine 2 LazyLists. This could be used to create
        the cartesian product of 2 lists, for example.
    */
    public <U,R> LazyList<R> combine(LazyList<U> other, BiFunction<T,U,R> combiner) {
        return this.flatmap(x ->
                            other.map(y -> combiner.apply(x,y)));
    }

    /** **************
        Just like map, this will apply eat on every element in the list,
        but eat is a Consumer, ie returns void. Typically used to print.
        Example: if Lyst is a list of lists, then Lyst.forEach(LazyList::print)
        will print each nested list on a separate line.
    */
    public void forEach(Consumer<T> eat) {
        if (this.isEmpty())
            return;
        else {
            eat.accept(this.head());
            this.tail().forEach(eat);
        }
    }
                                     
}



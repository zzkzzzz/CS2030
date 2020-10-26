import java.time.Instant;
import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.OptionalDouble;

public class LLTest {

    static LazyList<Integer> integers;
    static LazyList<Integer> fib;

    
    static LazyList<Integer> integersFrom(int n) {
        return LLmake(n, integersFrom(n + 1));
    }

    static LazyList<Integer> sieve(LazyList<Integer> s) {
        return LLmake(s.head(),
                      sieve(s.tail().filter(x-> (x%s.head()) != 0 )));
    }


    
    public static <T> T timeIt(Supplier<T> s, String msg) {
        Instant start, end;
        long timeTaken;
        T value;
        start = Instant.now();
        value = s.get();
        end = Instant.now();
        timeTaken = Duration.between(start,end).toMillis();
        System.out.println(String.format("%s took %d ms",
                                         msg,
                                         timeTaken));
        return value;
    }




    public static void main(String[] args) {
        // 0, 1, 1, 2, 3, 5, 8, 13, 21 ...
        fib = LLmake(0,
                     LLmake(1,
                            fib.elementWiseCombine(fib.tail(),
                                                   (x,y)->x+y)));
        timeIt(()-> fib.get(10), "fib 10");
        timeIt(()-> fib.get(10), "fib 10");
        timeIt(()-> fib.get(20), "fib 20");                
        timeIt(()-> fib.get(15), "fib 15");
        Memo.printGetTable();
        

    }
}
























        // var primes = sieve(integersFrom(2));

        // integers = LLmake(1, integers.map(x->x+1));
        // fib = LLmake(0,
        //              LLmake(1,
        //                     fib.elementWiseCombine(fib.tail(), (x,y)->x+y)));

        // System.out.println("----Primes----");
        // primes.limit(50).print();

        // System.out.println("----Intgers----");
        // integers.limit(50).print();
        
        // System.out.println("----Fibonacci----");        
        // fib.limit(30).print(); 

 // ;                
 //        Memo.printGetTable();

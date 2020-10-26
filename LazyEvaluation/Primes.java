import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
  Program to compare the computation efficiency of LazyLists
  versus ArrayLists.
  You may just run the given Primes.class, without compiling.

  See printUsage() for how to run it.

  To compile, use: jpp Primes.java
  Both Primes.class and LazyList.class should be in the "processed" directory.
*/
public class Primes {

    /** ***************
        Finds the kth prime in a given range by:
        1. Generating an LazyList of ints from a to b
        2. Filter this list with prime predicate.
        
        Notice that the predicate is instrumented to count
        the number of times it is called.
     */
    static void findKthPrimeLL(int a, int b, int k) {
        int prime = LazyList.intRange(a, b)
            .filter(predInstrument(Primes::isPrime))
            .get(k-1);

        printPrime(k, prime);
        printCounter();
    }

    static void findKthPrimeFromBackLL(int a, int b, int k){
        int prime = LazyList.intRange(a, b)
            .filter(predInstrument(Primes::isPrime))
            .reverse()
            .get(k-1);

        printPrime(k, prime);
        printCounter();
    }
    
    /** ***************
        Finds the kth prime in a given range by:
        1. Generating an ArrayList of ints from a to b
        2. Filter this list with prime predicate.
        
        Notice that the predicate is instrumented to count
        the number of times it is called.
     */
    static void findKthPrimeArr(int a, int b, int k) {
        var primesArrayList = listFilter(predInstrument(Primes::isPrime),
                                         intRangeArr(a, b));

        int prime = primesArrayList.get(k - 1);

        printPrime(k, prime);
        printCounter();
    }
    static void findKthPrimeFromBackArr(int a, int b, int k) {
        var primesArrayList = listFilter(predInstrument(Primes::isPrime),
                                         intRangeArr(a, b));

        int prime = primesArrayList.get(primesArrayList.size()-k);

        printPrime(k, prime);
        printCounter();
    }



    /** *************
       Static counter to count the number of times a predicate
       is invoked. Convenience methods to increment and print
       the counter are also provided.
     */
    static int counter = 0;
    
    static void incrCounter() {
        counter++;
    }

    static void printCounter() {
        System.out.println(String.format("isPrime was called %d times", counter));
    }

    /** *************
       Prints the usage of this program.
     */
    static void printUsage() {
        System.out.println("Usage: java Primes <a> <b> <k> <w>");
        System.out.println("where:  <a> is the lower limit of the range,");
        System.out.println("\t<b> is the upper limit of the range,") ;
        System.out.println("\t<k> is the k-th prime to find,");
        System.out.println("\t<w> is 0 (run the ArrayList version) or 1 (the LazyList version)");
        System.out.println("\n*** Note that <a> must be larger than 1.\n");
        System.out.println("To time how fast it runs, use the time bash command");
        System.out.println("\t\t eg: time java Primes 2 1000 3 1");
        System.out.println("\twill find the 3rd prime between 2 and 1000 using");
        System.out.println("\tthe LazyList version, and report the time taken to run.\n"); 
    }

    /** *************
       Convenience functions to parse command line input,
       and to print the result. 
       Also a function to instrument a predicate.
     */
    static int[] parse(String[] args) {
        if (args.length != 4) {
            printUsage();
            throw new RuntimeException();
        }

        int[] result = new int[4];
        for (int i = 0; i < 4; i++)
            result[i] = Integer.parseInt(args[i]);

        return result;
    }

    static void printPrime(int k, int p) {
        System.out.println(String.format("The %d-th prime is %d", k, p));
    }

    static <T> Predicate<T> predInstrument(Predicate<T> p) {
        return x-> { incrCounter();
                     return p.test(x);};
    }
    
    

    /** *************
        Various methods to generate an ArrayList of primes.
     */
    
    static List<Integer> intRangeArr(int a, int b) {
        List<Integer> result = new ArrayList<>();
        for (int x = a; x < b; x++)
            result.add(x);
        return result;
    }

    static <T> List<T> listFilter(Predicate<T> p, List<T> list) {
        List<T> newList = new ArrayList<>();
        for (T item : list)
            if (p.test(item))
                newList.add(item);
        return newList;
    }

   
    /** ***************
        Predicate to test if n is prime.
        This is a naive algorithm that performs trial division:
        it checks if n is divisible by all integers from 2 to to sqrt(n)
     */
    static boolean isPrime(int n) {
        for (int i = 2; i*i <= n; i++)
            if (n%i == 0)
                return false;
        return true;
    }

    
    /** ***************
        Main method
     */
    public static void main(String[] args) {

        // See printUsage for how to run this program.
        var inputs = parse(args);
        if (inputs[3] == 0)
            findKthPrimeArr(inputs[0], inputs[1], inputs[2]);
        else if (inputs[3] == 1)
            findKthPrimeLL(inputs[0], inputs[1], inputs[2]);

        else if (inputs[3] == 2)
            findKthPrimeFromBackArr(inputs[0], inputs[1], inputs[2]);
        
        else if (inputs[3] == 3)
            findKthPrimeFromBackLL(inputs[0], inputs[1], inputs[2]);

        else
            System.out.println("Wrong value for <w>!");
    }

}



    

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Puzzle {

    static boolean pzczSatisfies(LazyList<Integer> term) {
        int c = term.get(0);
        int h = term.get(1);
        int m = term.get(2);
        int p = term.get(3);
        int u = term.get(4);
        int z = term.get(5);

        int pzcz = p*1000 + z*100 + c*10 + z;
        int muchz = m*10000 + u*1000 + c*100 + h*10 + z;
        
        return m!=0 && p!=0 && (pzcz*15 == muchz);
    }

    // SEND + MORE = MONEY
    static boolean moneySatisfies(LazyList<Integer> term) {
        int d = term.get(0);
        int e = term.get(1);
        int m = term.get(2);
        int n = term.get(3);
        int o = term.get(4);
        int r = term.get(5);
        int s = term.get(6);
        int y = term.get(7);

        int send = s*1000 + e*100 + n*10 + d;
        int more = m*1000 + o*100 + r*10 + e;
        int money = m*10000 + o*1000 + n*100 + e*10 + y;
        
        return m!=0 && s!=0 && (send + more == money);
    }

    static void bruteForceForLoop() {
        List<List<Integer>> allSolutions = new ArrayList<>();
        
        for (int c=0; c<10; c++)
            for (int h=0; h<10; h++)
                if (h!=c)
                    for (int m=1; m<10; m++)
                        if (m!=c && m!=h)
                            for (int p=1; p<10; p++)
                                if (p!=c && p!=h && p!=m)
                                    for (int u=0; u<10; u++)
                                        if (u!=c && u!=h && u!=m && u!=p)
                                            for (int z=0; z<10; z++)
                                                if (z!=c && z!=h && z!=m && z!=p && z!=u) {
                                                    int pzcz = p*1000 + z*100 + c*10 + z;
                                                    int muchz = m*10000 + u*1000 + c*100 + h*10 + z;
                                                    if (pzcz*15 == muchz)
                                                        allSolutions.add(Arrays.asList(c,h,m,p,u,z));
                                                }
        
        System.out.println(allSolutions);
    }

    static void betterForLoop() {
        List<Integer> allSolutions = new ArrayList<>();
        
        for (int n=1000; n<10000; n++)
            if (isPZCZ(n) && isProductMuchz(n))
                allSolutions.add(n);

        System.out.println(allSolutions);
    }

    static boolean isPZCZ(int n) {
        /* check that: n has 4 digits, its format is PZCZ,
           and P,C,Z are distinct
         */
        if (!(1000 <= n && n<10000))
            return false;
        
        String strPZCZ = String.valueOf(n);
        char p = strPZCZ.charAt(0);
        char z = strPZCZ.charAt(1);
        char c = strPZCZ.charAt(2);        
        char last = strPZCZ.charAt(3);
        
        return z==last && p!=z && p!=c && z!=c;
    }

    static boolean isProductMuchz(int pzcz) {
        /* check that: pzcz*15 has 5 digits, it has C,Z in the right positions,
           and all digits are distinct from PZCZ. */
        
        int muchz = pzcz * 15;
        if (!(10000<= muchz && muchz<100000))
            return false;

        String strPZCZ = String.valueOf(pzcz);
        char p = strPZCZ.charAt(0);
        char z = strPZCZ.charAt(1);        
        char c = strPZCZ.charAt(2);
        
        String strMUCHZ = String.valueOf(muchz);
        char mm = strMUCHZ.charAt(0);
        char uu = strMUCHZ.charAt(1);
        char cc = strMUCHZ.charAt(2);
        char hh = strMUCHZ.charAt(3);
        char zz = strMUCHZ.charAt(4);
        
        return z==zz && c==cc && mm!=p && mm!=z && mm!=c &&
            uu!=mm && uu!=p && uu!=z && uu!=c &&
            hh!=mm && hh!=uu && hh!=p && hh!=z && hh!=c;
    }

    // We can use the same idea with IntStream:
    static void streamSolution() {
        IntStream.range(1000,10000)
            .filter(Puzzle::isPZCZ)
            .filter(Puzzle::isProductMuchz)
            .forEach(System.out::println);
    }


    public static LazyList<LazyList<Integer>> permute(LazyList<Integer> LL, int r) {
        if (r == 1)
            return LL.map(x-> LLmake(x, LazyList.makeEmpty()));
        else
            return LL.flatmap(x ->
                              permute(remove(LL, x), r - 1)
                              .map(y -> LLmake(x,y)));
    }

    public static LazyList<Integer> remove(LazyList<Integer> LL, int n) {
        return LL.filter(x-> x != n);
    }

    /**  ******
         Remember to increase the stack size when you run this program:
         java -Xss64m Puzzle
     */
    public static void main(String[] args) {
        permute(LazyList.intRange(0,10), 6)
            .filter(Puzzle::pzczSatisfies)
            .forEach(LazyList::print); 

        bruteForceForLoop();

        permute(LazyList.intRange(0,10), 8)
            .filter(Puzzle::moneySatisfies)
            .forEach(LazyList::print);
        
        betterForLoop();
        streamSolution();
        
    }

}

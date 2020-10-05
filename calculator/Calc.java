
//Calc.java
import java.util.NoSuchElementException;

/**
 * Calc.java implements a simple, command-line calculator. User enters command
 * in the form: <command> <arg1> <arg2> Example: add 3 5
 * 
 * Currently, only 4 command are implemented: add, mult, recip, %, denoting
 * addition, multiplcation, reciprocal, and percentage, respectively.
 * 
 * To compile, put all the files in the same directory: Calc.java, Input.java,
 * Statement.java, Results.java
 * 
 * Then type:
 * 
 * javac *.java java Calc
 */

public class Calc {
    public static void main(String[] args) {
        Statement.initialize();

        try {
            while (true) {
                Input input = Input.readInput();
                Statement statement = Statement.parse(input);
                Result result = statement.evaluate();
                result.display();
            }
        } catch (NoSuchElementException e) {
            // break out of while loop; end program
        }
    }
}

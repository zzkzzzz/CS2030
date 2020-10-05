//Result.java
/**
 * This class serves only to store the result of the calculation in the form of
 * a message string. A display method is provided to print the message to
 * console (System.out).
 * 
 */

public class Result {
    private final String message;

    public Result(String m) {
        this.message = m;
    }

    public void display() {
        System.out.println(message);
    }

}

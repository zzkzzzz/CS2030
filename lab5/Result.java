import java.util.ArrayList;

/**
 * This Result is used store result of query search of the grade in the form of
 * message string
 * 
 */
public class Result {
    private final ArrayList<String> result;

    /**
     * Result Constructor to initialize a arrylist to store result
     */
    public Result() {
        this.result = new ArrayList<>();
    }

    /**
     * add the message to the result list
     * 
     * @param msg result of query search
     * 
     */
    public void add(String msg) {
        if (!msg.equals("")) {
            result.add(msg);
        }
    }

    /**
     * print all the results in the list
     */
    public void printAll() {
        result.forEach((item) -> System.out.println(item));
    }

}

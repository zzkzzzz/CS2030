import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Result result = new Result();
        int lineNo = 0;

        try {
            // initialize the scanner
            // set the delimiting pattern of the Scanner
            Scanner sc = new Scanner(System.in).useDelimiter("\\n");
            Statement.setMaxRecord(sc.next());
            // keep read the user input
            while (sc.hasNextLine()) {
                Statement statement = Statement.parse(sc.next(), ++lineNo);
                result.add(statement.evaluate());
            }
        } catch (Exception e) {
            // print result after user terminates the program
            result.printAll();
        }

    }
}

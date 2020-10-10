import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Result result = new Result();
        int lineNo = 0;
        try {
            Scanner sc = new Scanner(System.in).useDelimiter("\\n");
            Statement.setMaxRecord(sc.next());
            while (sc.hasNextLine()) {
                Statement statement = Statement.parse(sc.next(), ++lineNo);
                result.add(statement.evaluate());
            }
        } catch (Exception e) {
            result.printAll();
        }

    }
}

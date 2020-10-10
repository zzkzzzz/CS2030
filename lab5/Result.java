import java.util.ArrayList;

public class Result {
    private final ArrayList<String> result;

    public Result() {
        this.result = new ArrayList<>();
    }

    public void add(String msg) {
        if (!msg.equals("")) {
            result.add(msg);
        }
    }

    public void printAll() {
        result.forEach((item) -> System.out.println(item));
    }

}

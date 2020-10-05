import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private final List<Classes> list;

    Schedule() {
        this.list = new ArrayList<Classes>();
    }

    public List<Classes> add(Classes cla) {
        List<Classes> newList = new ArrayList<Classes>(this.list);
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).clashWith(cla)) {
                return newList;
            }
        }
        newList.add(cla);
        return newList;
    }

    @Override
    public String toString() {
        List<Classes> newList = new ArrayList<Classes>(this.list);
        String result = "";
        for (int i = 0; i < newList.size(); i++) {
            result = result.concat(newList.get(i).toString()).concat("\n");
        }

        return result;
    }

}

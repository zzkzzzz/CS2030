import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private final List<Classes> list;

    Schedule() {
        this.list = new ArrayList<Classes>();
    }

    Schedule(List<Classes> list) {
        this.list = list;
    }

    public Schedule add(Classes cla) {
        List<Classes> newList = new ArrayList<Classes>(this.list);
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).clashWith(cla)) {
                return this;
            }
        }
        newList.add(cla);
        return new Schedule(newList);
    }

    @Override
    public String toString() {
        List<Classes> newList = new ArrayList<Classes>(this.list);
        newList.sort(new ClassComparator());
        String result = "";
        for (int i = 0; i < newList.size(); i++) {
            result = result.concat(newList.get(i).toString()).concat("\n");
        }

        return result;
    }
}

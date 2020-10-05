import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GapBetweenLectures extends Constraint {

    @Override
    public boolean test(Schedule s) {
        HashMap<String, List<Classes>> venueMap = new HashMap<String, List<Classes>>();
        for (Classes cla : s) {
            if (cla.getType().equals("Lecture")) {
                List<Classes> classes = venueMap.get(cla.getVenueID());
                if (classes == null) {
                    classes = new ArrayList<Classes>();
                } else {
                    if (!this.hasTimeGap(cla, classes)) {
                        return false;
                    }
                }
                classes.add(cla);
                venueMap.put(cla.getVenueID(), classes);
            }
        }
        return true;
    }

    private boolean hasTimeGap(Classes cla1, List<Classes> list) {
        for (Classes cla2 : list) {
            if (Math.abs(cla1.getStartTime() - cla2.getStartTime()) < 1) {
                return false;
            }
        }
        return true;
    }
}

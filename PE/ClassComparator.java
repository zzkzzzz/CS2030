
import java.util.Comparator;

/**
 * The Comparator for Classes.
 * 
 */
public class ClassComparator implements Comparator<Classes> {
    @Override
    public int compare(Classes c1, Classes c2) {

        if (c1.getStartTime() == c2.getStartTime()) {
            if (c1.getmodCode().equals(c2.getmodCode())) {
                return c1.getClassID() - c2.getClassID();
            } else {
                int mod1 = Integer.parseInt(c1.getmodCode().substring(c1.getmodCode().length() - 4));
                int mod2 = Integer.parseInt(c2.getmodCode().substring(c2.getmodCode().length() - 4));
                return mod1 - mod2;
            }
        } else {
            return c1.getStartTime() - c2.getStartTime();
        }
    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sword extends Item {
    private final boolean taken;

    public Sword() {
        super(0, new ArrayList<String>(Arrays.asList("Sword is shimmering.")));
        this.taken = false;
    }

    public Sword(int status) {
        super(status, new ArrayList<String>(Arrays.asList("Sword is shimmering.")));
        this.taken = false;
    }

    public Sword(int status, List<String> list, boolean taken) {
        super(status, list);
        this.taken = taken;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public Sword take() {
        return new Sword(0, this.getStatusList(), true);
    }

    public Sword change() {
        return this;
    }
}

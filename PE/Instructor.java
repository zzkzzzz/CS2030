
public class Instructor {
    private final String name;

    Instructor(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object ins) {
        if (this == ins) {
            return true;
        } else if (ins instanceof Instructor) {
            Instructor newIns = (Instructor) ins;
            if (newIns.getName().equals(this.getName())) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

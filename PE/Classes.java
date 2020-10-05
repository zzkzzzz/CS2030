abstract class Classes {
    private final String modCode;
    private final int classID;
    private final String venueID;
    private final int startTime;
    private final Instructor ins;
    private final String type;

    Classes(String modCode, int classID, String venueID, Instructor ins, int startTime, String type) {
        this.modCode = modCode;
        this.classID = classID;
        this.venueID = venueID;
        this.startTime = startTime;
        this.ins = ins;
        this.type = type;
    }

    public int getClassID() {
        return classID;
    }

    public String getmodCode() {
        return modCode;
    }

    public Instructor getIns() {
        return ins;
    }

    public String getVenueID() {
        return venueID;
    }

    public int getStartTime() {
        return startTime;
    }

    public String getType() {
        return type;
    }

    public boolean hasSameModule(Classes cla) {
        if (this.getmodCode().equals(cla.getmodCode())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasSameInstructor(Classes other) {
        if (this.getIns().equals(other.getIns())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasSameVenue(Classes cla) {
        if (this.getVenueID().equals(cla.getVenueID())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasOverlappingTime(Classes cla) {

        if (this.type.equals(cla.getType())) {
            if (this.type.equals("Lecture")) {
                return Math.abs(cla.getStartTime() - this.getStartTime()) < 2;
            }
            if (this.type.equals("Tutorial")) {
                return Math.abs(cla.getStartTime() - this.getStartTime()) < 1;
            }
        } else {
            if (Math.abs(cla.getStartTime() - this.getStartTime()) < 2) {
                if (this.type.equals("Lecture")) {
                    return this.getStartTime() <= cla.getStartTime();
                } else if (cla.type.equals("Lecture")) {
                    return cla.getStartTime() <= this.getStartTime();
                }
            }
        }

        return false;
    }

    public boolean clashWith(Classes cla) {

        if (this.hasOverlappingTime(cla)) {
            // no two classes can have overlapping slots and same venue OR same instructor
            if (this.hasSameInstructor(cla) || this.hasSameVenue(cla))
                return true;
            // no two lecture classes can have overlapping time slots
            if (this.type.equals(cla.getType()) && this.type.equals("Lecture") && this.hasSameModule(cla))
                return true;
            // a lecture of a module cannot clash with a tutorial of the same modul
            if (!this.type.equals(cla.getType()) && this.hasSameModule(cla))
                return true;
        }

        return false;
    }

}

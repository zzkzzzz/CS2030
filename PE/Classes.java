abstract class Classes {
    private final String modCode;
    private final int classID;
    private final String venueID;
    private final int startTime;
    private final Instructor ins;

    Classes(String modCode, int classID, String venueID, Instructor ins, int startTime) {
        this.modCode = modCode;
        this.classID = classID;
        this.venueID = venueID;
        this.startTime = startTime;
        this.ins = ins;
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

    public boolean clashWith(Classes cla) {
        int startTime1 = this.getStartTime();
        int startTime2 = cla.getStartTime();
        String mod1 = this.getmodCode();
        String mod2 = cla.getmodCode();
        String v1 = this.getVenueID();
        String v2 = cla.getVenueID();

        if (startTime1 == startTime2 && v1 == v1) {
            return true;
        }

        if (cla instanceof Lecture) {
            if (mod1 == mod2) {
                if (Math.abs(startTime1 - startTime2) < 2) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;

    }

}

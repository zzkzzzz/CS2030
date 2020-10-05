public class Lecture extends Classes {
    Lecture(String modCode, int classID, String venueID, Instructor ins, int startTime) {
        super(modCode, classID, venueID, ins, startTime, "Lecture");
    }

    @Override
    public String toString() {
        return String.format("%s L%d @ %s [%s] %d--%d", this.getmodCode(), this.getClassID(), this.getVenueID(),
                this.getIns(), this.getStartTime(), this.getStartTime() + 2);
    }
}

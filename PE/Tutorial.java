public class Tutorial extends Classes {
    Tutorial(String modCode, int classID, String venueID, Instructor ins, int startTime) {
        super(modCode, classID, venueID, ins, startTime, "Tutorial");
    }

    @Override
    public String toString() {
        return String.format("%s T%d @ %s [%s] %d--%d", this.getmodCode(), this.getClassID(), this.getVenueID(),
                this.getIns(), this.getStartTime(), this.getStartTime() + 1);
    }
}

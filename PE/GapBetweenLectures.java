public class GapBetweenLectures extends Constraint {

    @Override
    public boolean test(Schedule s) {
        for (Classes cla1 : s) {
            if (cla1.getType().equals("Lecture")) {
                for (Classes cla2 : s) {
                    if (cla2.getType().equals("Lecture") && !cla1.clashWith(cla2) && cla1.hasSameVenue(cla2)
                            && !this.hasTimeGap(cla1, cla2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean hasTimeGap(Classes cla1, Classes cla2) {
        return Math.abs(cla1.getStartTime() - cla2.getStartTime()) >= 3;
    }
}

public class HchiaLunch extends Constraint {

    @Override
    public boolean test(Schedule s) {
        for (Classes cla : s) {
            if (cla.getIns().getName().equals("hchia") && this.isDuringLunch(cla)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDuringLunch(Classes cla) {
        int start = cla.getStartTime();
        int end = cla.getStartTime() + (cla.getType().equals("Tutorial") ? 1 : 2);

        return (start >= 14 && start < 16) || (end > 14 && end <= 16);

    }

}

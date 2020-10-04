
abstract class Service {
    // private static final int PEAKHOUR_START = 600;
    // private static final int PEAKHOUR_END = 900;

    public abstract int computeFare(Request request);

    // to the check the time is peak hour or not
    public boolean isPeak(int time) {
        return time <= 900 && time >= 600;
    }

    @Override
    public String toString() {
        return "Service";
    }
}

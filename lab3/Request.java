public class Request {
    private final int distance;
    private final int numOfPassengers;
    private final int time;

    Request(int distance, int numOfPassengers, int time) {
        this.distance = distance;
        this.numOfPassengers = numOfPassengers;
        this.time = time;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getNumOfPassengers() {
        return this.numOfPassengers;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.distance, this.numOfPassengers, this.time);
    }
}

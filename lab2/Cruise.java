package lab2;

public class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    Cruise(String identifier, int arrivalTime, int numOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoader = numOfLoader;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime / 100 * 60 + arrivalTime % 100;
    }

    public int getServiceCompletionTime() {
        return arrivalTime / 100 * 60 + arrivalTime % 100 + serviceTime;
    }

    public int getNumOfLoadersRequired() {
        return numOfLoader;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime);
    }

}

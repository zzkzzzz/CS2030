package lab2;

public class SmallCruise extends Cruise {
    // smallcruise requires only one loader for it to be fully served
    // private static final int numOfLoader = 1;
    // smallcruise takes a fixed 30 minutes for a loader to fully load
    // private static final int serviceTime = 30;

    SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, 1, 30);
    }

}

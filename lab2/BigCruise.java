package lab2;

public class BigCruise extends Cruise {

    private final int numOfPassengers;
    private final int lengthOfCruise;

    BigCruise(String identifier, int arrivalTime, int lengthOfCruise, int numOfPassengers) {
        super(identifier, arrivalTime, (int) Math.ceil((double) lengthOfCruise / 40.0),
                (int) Math.ceil((double) numOfPassengers / 50.0));
        this.numOfPassengers = numOfPassengers;
        this.lengthOfCruise = lengthOfCruise;
    }

}

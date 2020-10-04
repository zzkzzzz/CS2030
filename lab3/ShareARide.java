
public class ShareARide extends Service {
    // Fare is based on the distance @ 50 cents per km
    // private static final int UNIT_FARE = 50;
    // A surcharge of 500 cents if a ride request is issued betweenve PEAK HOUR
    // private static final int PEEAHOUR_FEE = 500;

    @Override
    public int computeFare(Request request) {
        if (this.isPeak(request.getTime()))
            return (request.getDistance() * 50 + 500) / request.getNumOfPassengers();

        return (request.getDistance() * 50) / request.getNumOfPassengers();
    }

    @Override
    public String toString() {
        return "ShareARide";
    }

}

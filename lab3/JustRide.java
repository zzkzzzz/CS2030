
public class JustRide extends Service {
    // Fare is based on the distance @ 22 cents per km
    // private static final int UNIT_FARE = 22;
    // A surcharge of 500 cents if a ride request is issued betweenve PEAK HOUR
    // private static final int PEEAHOUR_FEE = 500;

    @Override
    public int computeFare(Request request) {
        if (this.isPeak(request.getTime()))
            return request.getDistance() * 22 + 500;
        else
            return request.getDistance() * 22;

    }

    @Override
    public String toString() {
        return "JustRide";
    }
}

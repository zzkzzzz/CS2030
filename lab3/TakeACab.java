
public class TakeACab extends Service {
    // Fare is based on the distance @ 33 cents per km,
    // but there is a booking fee of 200 cents
    // private static final int UNIT_FARE = 33;
    // private static final int BOOKING_FEE = 200;

    @Override
    public int computeFare(Request request) {
        return request.getDistance() * 33 + 200;

    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}

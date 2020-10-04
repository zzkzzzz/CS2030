
public class PrivateCar extends Driver {

    PrivateCar(String license, int waitingTime) {
        // PrivateCar drivers provide JustRide and ShareARide services
        super(license, waitingTime, new Service[] { new JustRide(), new ShareARide() });
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) PrivateCar", this.getLicense(), this.getWaitingTime());
    }
}

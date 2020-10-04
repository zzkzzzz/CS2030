
public class NormalCab extends Driver {

    NormalCab(String license, int waitingTime) {
        // NormalCab drivers provide JustRide and TakeACab services
        super(license, waitingTime, new Service[] { new JustRide(), new TakeACab() });
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) NormalCab", this.getLicense(), this.getWaitingTime());
    }

}

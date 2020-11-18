package cs2030.simulator;

public class LeaveEvent extends Event {
    /**
     * Leave Event Constructor.
     *
     * @param customer   the customer object
     * @param serverList the list of servers
     */
    LeaveEvent(Customer customer) {
        super(3, customer, x -> new Pair<Shop, Event>(x, null));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        String id = this.getCustomer().getType();
        return String.format("%.3f %s leaves", startTime, id);
    }
}

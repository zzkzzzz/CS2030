package cs2030.simulator;

public class ServeEvent extends Event {
    /**
     * Serve Event Constructor.
     *
     * @param customer      the customer object
     * @param currentServer the current server
     * @param startTime     the start time of serve event
     */
    ServeEvent(Customer customer, Server currentServer, double startTime) {
        super(2, customer, currentServer, startTime, 
            x -> new Pair<Shop, Event>(x.replace(currentServer), 
            new DoneEvent(customer, currentServer.goRest(customer))));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        String id = this.getCustomer().getType();
        String name = this.getCurrentServer().name();
        return String.format("%.3f %s served by %s", startTime, id, name);
    }
}

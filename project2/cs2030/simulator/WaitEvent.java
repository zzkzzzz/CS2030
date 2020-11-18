package cs2030.simulator;

public class WaitEvent extends Event {
    /**
     * Wait Event Donstructor.
     *
     * @param customer      the customer object
     * @param currentServer the server who serve the customer
     */
    WaitEvent(Customer customer, Server currentServer) {
        super(1, customer, currentServer, customer.getArrivalTime(),
            x -> new Pair<Shop, Event>(x.replace(currentServer),
                    new ServeEvent(customer, 
                                   currentServer.serve(customer), 
                                   currentServer.getStartTime(customer))));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        String id = this.getCustomer().getType();
        String name = this.getCurrentServer().name();
        return String.format("%.3f %s waits to be served by %s", startTime, id, name);
    }
}

package cs2030.simulator;

public class DoneEvent extends Event {
    /**
     * Done Event Constructor.
     *
     * @param customer        the customer object
     * @param serverList      the list of servers
     * @param currentServerId the Id of the server who serve the customer
     */
    DoneEvent(Customer customer, Server currentServer) {
        // after DoneEvent, all server will rest
        // different server will have different rest time
        // self-checked counter will have 0 resting time
        super(3, customer, currentServer, currentServer.getNextAvailableTime().get(),
            x -> new Pair<Shop, Event>(x.replace(currentServer),
                        new ServerRestEvent(customer, currentServer.goRest(customer))));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        String id = this.getCustomer().getType();
        String name = this.getCurrentServer().name();
        return String.format("%.3f %s done serving by %s", startTime, id, name);
    }

}

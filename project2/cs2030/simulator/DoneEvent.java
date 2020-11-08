package cs2030.simulator;

import java.util.List;

public class DoneEvent extends Event {
    /**
     * Done Event Constructor.
     *
     * @param customer        the customer object
     * @param serverList      the list of servers
     * @param currentServerId the Id of the server who serve the customer
     */
    DoneEvent(Customer customer, Server currentServer) {
        super(customer, currentServer, currentServer.getNextAvailableTime(),
                x -> new Pair<Shop, Event>(x.replace(currentServer), null));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getCurrentServer().getIdentifier();
        return String.format("%.3f %d done serving by %d", startTime, customerId, serverId);
    }

}

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class ServeEvent extends Event {
    /**
     * Serve Event Donstructor.
     *
     * @param customer        the customer object
     * @param serverList      the list of servers
     * @param currentServerId the Id of the server who serve the customer
     */

    ServeEvent(Customer customer, Server currentServer, double startTime) {
        super(customer, currentServer, startTime,
                x -> new Pair<Shop, Event>(x.replace(currentServer), currentServer.done(customer)));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getCurrentServer().getIdentifier();
        return String.format("%.3f %d served by %d", startTime, customerId, serverId);
    }
}

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class WaitEvent extends Event {
    /**
     * Wait Event Donstructor.
     *
     * @param customer        the customer object
     * @param serverList      the list of servers
     * @param currentServerId the Id of the server who serve the customer
     */
    // WaitEvent(Customer customer, Server currentServer) {
    // super(customer, currentServer, customer.getArrivalTime(),
    // x -> new Pair<Shop, Event>(x.replace(currentServer),
    // new ServeEvent(customer,
    // currentServer.goBusyNoWait(currentServer.getNextAvailableTime() + 1),
    // currentServer.getNextAvailableTime())));
    // }

    WaitEvent(Customer customer, Server currentServer) {
        super(customer, currentServer, customer.getArrivalTime(), x -> new Pair<Shop, Event>(x.replace(currentServer),
                currentServer.serve(customer)));
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getCurrentServer().getIdentifier();
        return String.format("%.3f %d waits to be served by %d", startTime, customerId, serverId);
    }
}

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
    WaitEvent(Customer customer, List<Server> serverList, int currentServerId) {
        super(customer, serverList, currentServerId);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public double calculateStartTime() {
        return this.getCustomer().getArrivalTime();
    }

    // WAIT → SERVE
    @Override
    public Event execute() {
        List<Server> newServerList = new ArrayList<>(this.getServerList());
        Server server = newServerList.get(this.getCurrentServerId());
        Server newServer = server.goBusyNoWait(server.getNextAvailableTime() + 1);
        newServerList.set(this.getCurrentServerId(), newServer);
        return new ServeEvent(this.getCustomer(), newServerList, this.getCurrentServerId());
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getServerList().get(this.getCurrentServerId()).getIdentifier();
        return String.format("%.3f %d waits to be served by %d", startTime, customerId, serverId);
    }
}

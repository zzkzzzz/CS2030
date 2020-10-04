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
    ServeEvent(Customer customer, List<Server> serverList, int currentServerId) {
        super(customer, serverList, currentServerId);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public double calculateStartTime() {
        // ARRIVE → SERVE
        // if customer arrival time is larger than server's next available time
        // which means this customer is just arrived
        double customerArrivalTime = this.getCustomer().getArrivalTime();
        int serverId = this.getCurrentServerId();
        double serverNextAvailableTime = this.getServerList().get(serverId).getNextAvailableTime();
        if (customerArrivalTime >= serverNextAvailableTime) {
            return this.getCustomer().getArrivalTime();
        } else {
            // WAIT → SERVE
            // if customer arrival time is less than server's next available time
            // which means this customer has been waited for some time
            return this.getServerList().get(this.getCurrentServerId()).getNextAvailableTime() - 1;
        }

    }

    // SERVE → DONE
    @Override
    public Event execute() {
        List<Server> newServerList = new ArrayList<Server>(this.getServerList());
        Server server = newServerList.get(this.getCurrentServerId());
        Server newServer = server.goAvailable(server.getNextAvailableTime());
        newServerList.set(this.getCurrentServerId(), newServer);
        return new DoneEvent(this.getCustomer(), newServerList, this.getCurrentServerId());
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getServerList().get(this.getCurrentServerId()).getIdentifier();
        return String.format("%.3f %d served by %d", startTime, customerId, serverId);
    }
}

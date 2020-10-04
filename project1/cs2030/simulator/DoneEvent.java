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
    DoneEvent(Customer customer, List<Server> serverList, int currentServerId) {
        super(customer, serverList, currentServerId);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public double calculateStartTime() {
        return this.getServerList().get(this.getCurrentServerId()).getNextAvailableTime();
    }

    @Override
    public Event execute() {
        return null;
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        int serverId = this.getServerList().get(this.getCurrentServerId()).getIdentifier();

        return String.format("%.3f %d done serving by %d", startTime, customerId, serverId);
    }

}

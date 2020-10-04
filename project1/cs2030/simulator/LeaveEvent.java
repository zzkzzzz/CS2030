package cs2030.simulator;

import java.util.List;

public class LeaveEvent extends Event {
    /**
     * Leave Event Constructor.
     *
     * @param customer   the customer object
     * @param serverList the list of servers
     */
    LeaveEvent(Customer customer, List<Server> serverList) {
        super(customer, serverList);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public double calculateStartTime() {
        return this.getCustomer().getArrivalTime();
    }

    @Override
    public Event execute() {
        return null;
    }

    @Override
    public String toString() {
        double startTime = this.getStartTime();
        int customerId = this.getCustomer().getId();
        return String.format("%.3f %d leaves", startTime, customerId);
    }
}

package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class ArriveEvent extends Event {
    /**
     * Arrive Event Constructor.
     *
     * @param customer   the customer object
     * @param serverList the list of servers
     */
    ArriveEvent(Customer customer, List<Server> serverList) {
        super(customer, serverList);
    }

    public int getPriority() {
        return 0;
    }

    @Override
    public double calculateStartTime() {
        return this.getCustomer().getArrivalTime();
    }

    @Override
    public Event execute() {
        List<Server> newServerList = new ArrayList<Server>(this.getServerList());
        double arrivesTime = this.getCustomer().getArrivalTime();

        // ARRIVE → SERVE
        // The customer scans the servers and approaches the first available
        // server to be serverd
        for (int i = 0; i < newServerList.size(); i++) {
            Server server = newServerList.get(i);
            if (server.getIsAvailable()) {
                Server newServer = server.goBusyNoWait(arrivesTime + 1);
                newServerList.set(i, newServer);
                return new ServeEvent(this.getCustomer(), newServerList, i);
            }

        }

        // ARRIVE → WAIT
        // If no available server, the customer scans the server and waits
        // at the first busy server without a waiting customer
        for (int i = 0; i < newServerList.size(); i++) {
            Server server = newServerList.get(i);
            if (!server.getHasWaitingCustomer()) {
                Server newServer = server.goBusyNeedWait(server.getNextAvailableTime());
                newServerList.set(i, newServer);
                return new WaitEvent(this.getCustomer(), newServerList, i);
            }
        }

        // ARRIVE → LEAVE
        // If all servers are busy and already and have a customer waiting,
        // then the customer will leave
        return new LeaveEvent(this.getCustomer(), newServerList);
    }

    @Override
    public String toString() {
        double arriveTime = this.getCustomer().getArrivalTime();
        int id = this.getCustomer().getId();
        return String.format("%.3f %d arrives", arriveTime, id);
    }

}

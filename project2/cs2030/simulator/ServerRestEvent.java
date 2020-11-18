package cs2030.simulator;

public class ServerRestEvent extends Event {

    /**
     * Server Rest Event Constructor.
     * 
     * @param customer      the current customer
     * @param currentServer the current server
     */
    ServerRestEvent(Customer customer, Server currentServer) {
        super(4, customer, currentServer, 
            currentServer.getNextAvailableTime().get() - currentServer.getRestingTime(),
            x -> new Pair<Shop, Event>(x.replace(currentServer),
            new ServerBackEvent(customer, currentServer.goBack(customer))));

    }

    @Override
    public String toString() {
        int serverId = this.getCurrentServer().getIdentifier();
        return String.format("%.3f %d resting", this.getStartTime(), serverId);
    }

}

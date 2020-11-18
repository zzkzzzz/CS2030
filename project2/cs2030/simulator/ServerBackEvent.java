package cs2030.simulator;

public class ServerBackEvent extends Event {

    /**
     * ServerBack Event Constructor.
     * 
     * @param customer      the current customer
     * @param currentServer the current server
     */
    ServerBackEvent(Customer customer, Server currentServer) {
        super(5, customer, currentServer, currentServer.getNextAvailableTime().get(),
            x -> new Pair<Shop, Event>(x.replace(currentServer), null));
    }

    @Override
    public String toString() {
        int serverId = this.getCurrentServer().getIdentifier();
        return String.format("%.3f %d back", this.getStartTime(), serverId);
    }
}

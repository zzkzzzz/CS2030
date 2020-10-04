package cs2030.simulator;

public class Server {

    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /**
     * Server Constructor.
     *
     * @param identifier         the id of server
     * @param isAvailable        whether the server is currently available to serve
     * @param hasWaitingCustomer whether there is a waiting customer when bus
     * @param nextAvaiTime       the time when the server is able to serve the next
     *                           customer
     */
    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvaiTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvaiTime;
    }

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    public boolean getHasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public int getIdentifier() {
        return identifier;
    }

    /**
     * Change the status of the server to avalable.
     * 
     * @param next the next avaiable time of the server
     * @return return updated Server
     * 
     */
    public Server goAvailable(double next) {
        return new Server(this.identifier, true, false, next);
    }

    /**
     * Change the status of the server to busy but no waiting customer.
     * 
     * @param next the next avaiable time of the server
     * @return return updated Server
     * 
     */
    public Server goBusyNoWait(double next) {
        return new Server(this.identifier, false, false, next);
    }

    /**
     * Change the status of the server to busy and have waiting customer.
     * 
     * @param next the next avaiable time of the server
     * @return return updated Server
     * 
     */
    public Server goBusyNeedWait(double next) {
        return new Server(this.identifier, false, true, next);
    }

    @Override
    public String toString() {
        if (isAvailable && !hasWaitingCustomer) {
            return String.format("%d is available", identifier);
        } else if (!isAvailable && !hasWaitingCustomer) {
            return String.format("%d is busy; available at %.3f", identifier, nextAvailableTime);
        } else if (!isAvailable && hasWaitingCustomer) {
            String msg1 = String.format("%d is busy; ", identifier);
            String msg2 = String.format("waiting customer to be served at %.3f", nextAvailableTime);
            return msg1 + msg2;
        } else {
            return "Invaild Server";
        }

    }
}

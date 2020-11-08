package cs2030.simulator;

import java.util.LinkedList;
import java.util.Queue;

public class Server {

    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final Customer currentCustomer;
    private final LinkedList<Customer> waitingQueues;
    private final int capacity;

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
        this.currentCustomer = null;
        this.capacity = 1;
        this.waitingQueues = new LinkedList<>();
    }

    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvaiTime, int capacity) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvaiTime;
        this.currentCustomer = null;
        this.capacity = capacity;
        this.waitingQueues = new LinkedList<>();
    }

    public Event serve(Customer customer) {
        if (this.isAvailable())
            return new ServeEvent(customer, this.goBusyNoWait(customer.getArrivalTime() + 1),
                    customer.getArrivalTime());
        else if (!isFull()) {
            this.waitingQueues.push(customer);
            return new WaitEvent(customer, this.goBusyNeedWait(this.getNextAvailableTime()));
        } else
            return new LeaveEvent(customer);
    }

    public Event done(Customer customer) {
        return new DoneEvent(customer, this.goAvailable(this.getNextAvailableTime()));
    }

    public boolean isFull() {
        return this.waitingQueues.size() == capacity;
    }

    public Customer nextCustomer() {
        return this.waitingQueues.pop();
    }

    public Server next() {
        return new Server(this.getIdentifier() + 1, true, false, 0);
    }

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    public boolean hasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    public boolean isAvailable() {
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

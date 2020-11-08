package cs2030.simulator;

import java.util.List;
import java.util.function.Function;

public abstract class Event {
    private final double startTime;
    private final Customer customer;
    // private final List<Server> serverList;
    // // store the id of the current server that serve the customer
    private final Server currentServer;

    private final Function<Shop, Pair<Shop, Event>> func;

    Event(Customer customer, Function<Shop, Pair<Shop, Event>> func) {
        this.customer = customer;
        this.func = func;
        this.currentServer = null;
        this.startTime = customer.getArrivalTime();
    }

    Event(Customer customer, Server currentServer, double startTime, Function<Shop, Pair<Shop, Event>> func) {
        this.customer = customer;
        this.func = func;
        this.currentServer = currentServer;
        this.startTime = startTime;
    }

    /**
     * execcute current event.
     * 
     * @return return next event
     */
    final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }

    /**
     * get the Cutomer in this event.
     * 
     * @return return the Customer object in this event
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * get the start time of current event.
     * 
     * @return return the start time of current event
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * get the id of current server who serve the customer.
     * 
     * @return return the id of current server who serve the customer
     */
    public Server getCurrentServer() {
        return currentServer;
    }

}
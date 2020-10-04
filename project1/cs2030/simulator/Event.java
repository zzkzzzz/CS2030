package cs2030.simulator;

import java.util.List;

public abstract class Event {
    private final double startTime;
    private final Customer customer;
    private final List<Server> serverList;
    // store the id of the current server that serve the customer
    private final int currentServerId;

    /**
     * Event Constructor for arrive Event and leave Event, no current server exits.
     *
     * @param customer   the customer object
     * @param serverList the list of servers
     */
    Event(Customer customer, List<Server> serverList) {
        this.customer = customer;
        this.serverList = serverList;
        this.startTime = this.calculateStartTime();
        this.currentServerId = -1;
    }

    /**
     * Overloaded Event Constructor for Serve, Done, Wait Events. These events all
     * have server to serve.
     *
     * @param customer        the customer object
     * @param serverList      the list of servers
     * @param currentServerId the id of the server who serve the customer
     */
    Event(Customer customer, List<Server> serverList, int currentServerId) {
        this.customer = customer;
        this.serverList = serverList;
        this.currentServerId = currentServerId;
        this.startTime = this.calculateStartTime();
    }

    /**
     * get the Server List in this event.
     * 
     * @return return the Server List in this event
     */
    public List<Server> getServerList() {
        return serverList;
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
    public int getCurrentServerId() {
        return currentServerId;
    }

    /**
     * calculate the start time of current event.
     * 
     * @return return start time of current event
     */
    public abstract double calculateStartTime();

    /**
     * To get the priority level of the event.
     * 
     * @return return the Priority level of the ArriveEvent
     */
    public abstract int getPriority();

    /**
     * execcute current event.
     * 
     * @return return next event
     */
    public abstract Event execute();

}
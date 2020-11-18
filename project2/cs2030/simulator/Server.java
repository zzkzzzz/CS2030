package cs2030.simulator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Server {

    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final Lazy<Double> nextAvailableTime;
    private final Customer currentCustomer;
    private final LinkedList<Customer> waitingQueues;
    private final int capacity;
    private final double restingTime;
    private final boolean isSelfChecked;

    /**
     * Server Constructor. Default constructor queue capacity=1 resting time=0.
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
        this.nextAvailableTime = Lazy.of(nextAvaiTime);
        this.currentCustomer = null;
        this.capacity = 1;
        this.restingTime = 0;
        this.isSelfChecked = false;
        if (hasWaitingCustomer) {
            this.waitingQueues = new LinkedList<>(Arrays.asList(new Customer(0, 0)));
        } else {
            this.waitingQueues = new LinkedList<>();
        }
    }

    /**
     * Server Constructor.
     * 
     * @param identifier         the id of server
     * @param isAvailable        whether the server is currently available to serve
     * @param hasWaitingCustomer whether there is a waiting customer when bus
     * @param nextAvaiTime       the time when the server is able to serve the next
     *                           customer
     * @param capacity           the maximun queue size of the server
     * @param waitingQueues      the waiting queue
     * @param customer           the current serving customer
     * @param isSelfChecked      the server is self-checked counter or not
     */
    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, 
        Lazy<Double> nextAvaiTime, int capacity, LinkedList<Customer> waitingQueues, 
        Customer customer, boolean isSelfChecked) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvaiTime;
        this.currentCustomer = customer;
        this.capacity = capacity;
        this.restingTime = 0;
        this.waitingQueues = new LinkedList<>(waitingQueues);
        this.isSelfChecked = isSelfChecked;
    }

    /**
     * Server Constructor to set the resting time.
     * 
     * @param server      Server
     * @param restingTime resting time
     */
    Server(Server server, double restingTime) {
        this.identifier = server.getIdentifier();
        this.isAvailable = server.isAvailable();
        this.hasWaitingCustomer = server.hasWaitingCustomer();
        this.nextAvailableTime = server.getNextAvailableTime();
        this.currentCustomer = server.getCurrentCustomer();
        this.capacity = server.getCapacity();
        this.restingTime = restingTime;
        this.waitingQueues = new LinkedList<>(server.getWaitingQueues());
        this.isSelfChecked = server.isSelfChecked();
    }

    /**
     * Server Constructor to set the waiting queue.
     * 
     * @param server        server
     * @param waitingQueues waitingQueues
     */
    Server(Server server, List<Customer> waitingQueues) {
        this.identifier = server.getIdentifier();
        this.isAvailable = server.isAvailable();
        this.hasWaitingCustomer = server.hasWaitingCustomer();
        this.nextAvailableTime = server.getNextAvailableTime();
        this.currentCustomer = server.getCurrentCustomer();
        this.capacity = server.getCapacity();
        this.restingTime = 0;
        this.waitingQueues = new LinkedList<>(waitingQueues);
        this.isSelfChecked = server.isSelfChecked();
    }

    /**
     * return the updated server after serve the customer.
     * 
     * @param customer customer to be served
     * @return updated server
     */
    public Server serve(Customer customer) {
        // if the server is free, let the server serve the customer
        // nextAvailableTime will be the arrivel time of the customer + serve time
        // event start time is the arrivel time of the customer

        // if the customer is waiting in the queue before, poll it
        if (!this.isEmpty() && this.waitingQueues.indexOf(customer) >= 0) {
            LinkedList<Customer> newQ = new LinkedList<Customer>(this.waitingQueues);
            // newQ.poll();

            while (!newQ.isEmpty() && newQ.peek().getId() != customer.getId()) {
                newQ.poll();
            }
            newQ.poll();

            Server newServer;
            if (newQ.size() == 0) {
                Supplier<Double> s = () -> (this.getStartTime(customer) + customer.get());
                Lazy<Double> zz = Lazy.of(s);
                newServer = this.goBusyNoWait(zz, customer);
            } else {
                Supplier<Double> s = () -> (this.getStartTime(customer) + customer.get());
                Lazy<Double> zz = Lazy.of(s);
                newServer = this.goBusyNeedWait(zz, customer);
            }

            return new Server(newServer, newQ);
        }
        Supplier<Double> s = () -> (customer.getArrivalTime() + customer.get());
        Lazy<Double> zz = Lazy.of(s);
        Server newServer = this.goBusyNoWait(zz, customer);
        return newServer;
    }

    /**
     * return updated server after a customer wait in the queue.
     * 
     * @param customer the customer should waiting in the queue
     * @return updated sever after a customer wait
     */
    public Server wait(Customer customer) {
        // the server is not free, the waiting queue is not full
        // add the customer to waiting queue
        LinkedList<Customer> newQ = new LinkedList<>(this.waitingQueues);
        newQ.add(customer);

        return new Server(this, newQ);
    }

    /**
     * get the serve event start time of given customer.
     * 
     * @param customer the customer to be served
     * @return start time of serve time
     */
    public double getStartTime(Customer customer) {
        double startTime = this.getNextAvailableTime().get();

        LinkedList<Customer> queue = new LinkedList<>(this.waitingQueues);
        while (!queue.isEmpty() && queue.peek().getId() != customer.getId()) {
            startTime = startTime + queue.poll().get();

        }

        return startTime;
    }

    /**
     * check empty of the sever wait queue.
     * 
     * @return the queue is empty or not
     */
    public boolean isEmpty() {
        return this.waitingQueues.isEmpty();
    }

    /**
     * check the waiting queue is full or not.
     * 
     * @return the queue is full or not
     */
    public boolean isFull() {
        return this.waitingQueues.size() == capacity;
    }

    /**
     * generate defalut server.
     * 
     * @return defalut server
     */
    public Server next() {
        return new Server(this.getIdentifier() + 1, true, false, 0);
    }

    /**
     * Lazily get next available time of the server. When the customer is being
     * served, the value will be evaluated.
     * 
     * @return next available time
     */
    public Lazy<Double> getNextAvailableTime() {
        return nextAvailableTime;
    }

    /**
     * the server has waiting customer or not.
     * 
     * @return hasWaitingCustomer
     */
    public boolean hasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    /**
     * the server is avaiable or not.
     * 
     * @return isAvailable
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * get id of the server.
     * 
     * @return identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * get current customer that serve by the server.
     * 
     * @return current customer
     */
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    /**
     * get capacity of the waiting queue.
     * 
     * @return capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * get waiting queue of the server.
     * 
     * @return waiting queue
     */
    public LinkedList<Customer> getWaitingQueues() {
        return new LinkedList<>(this.waitingQueues);
    }

    /**
     * get resting time of the server.
     * 
     * @return resting time
     */
    public double getRestingTime() {
        return this.restingTime;
    }

    /**
     * the server is self-checked counter or not.
     * 
     * @return isSelfChecked
     */
    public boolean isSelfChecked() {
        return this.isSelfChecked;
    }

    /**
     * change the status of server to resting.
     * 
     * @param customer the customer to be served after resting
     * @return updated server
     */
    public Server goRest(Customer customer) {
        // self-checked counter no resting time
        if (isSelfChecked) {
            return new Server(this.identifier, false, !this.waitingQueues.isEmpty(),
                    Lazy.of(this.nextAvailableTime.get()), this.capacity, this.waitingQueues, 
                    customer, this.isSelfChecked);
        }
        return new Server(this.identifier, false, !this.waitingQueues.isEmpty(),
                Lazy.of(this.nextAvailableTime.get() + this.restingTime), this.capacity, 
                this.waitingQueues, customer, this.isSelfChecked);
    }

    /**
     * change the status of server to back after resting.
     * 
     * @param customer the customer to be served after resting
     * @return updated server
     */
    public Server goBack(Customer customer) {
        return new Server(this.identifier, true, !this.waitingQueues.isEmpty(), 
                Lazy.of(this.nextAvailableTime.get()), this.capacity, this.waitingQueues, 
                customer, this.isSelfChecked);
    }

    /**
     * Change the status of the server to avalable.
     * 
     * @param next the next avaiable time of the server
     * @return updated Server
     * 
     */
    public Server goAvailable(Lazy<Double> next) {
        return new Server(this.identifier, true, false, next, this.capacity, this.waitingQueues, 
                            null, this.isSelfChecked);
    }

    /**
     * Change the status of the server to busy but no waiting customer.
     * 
     * @param next the next avaiable time of the server
     * @return return updated Server
     * 
     */
    public Server goBusyNoWait(Lazy<Double> next, Customer customer) {
        return new Server(this.identifier, false, false, next, this.capacity, this.waitingQueues, 
                customer, this.isSelfChecked);
    }

    /**
     * Change the status of the server to busy and have waiting customer.
     * 
     * @param next the next avaiable time of the server
     * @return return updated Server
     * 
     */
    public Server goBusyNeedWait(Lazy<Double> next, Customer customer) {
        return new Server(this.identifier, false, true, next, this.capacity, this.waitingQueues, 
                    customer, this.isSelfChecked);
    }

    /**
     * String representation of the server.
     * 
     * @return String representation
     */
    public String name() {
        if (isSelfChecked()) {
            return String.format("self-check %d", this.identifier);
        }
        return String.format("server %d", this.identifier);
    }

    @Override
    public String toString() {
        if (isAvailable && !hasWaitingCustomer) {
            return String.format("%d is available", identifier);
        } else if (!isAvailable && !hasWaitingCustomer) {
            return String.format("%d is busy; available at %.3f", identifier, 
                                    nextAvailableTime.get());
        } else if (!isAvailable && hasWaitingCustomer) {
            String msg1 = String.format("%d is busy; ", identifier);
            String msg2 = String.format("waiting customer to be served at %.3f", 
                                    nextAvailableTime.get());
            return msg1 + msg2;
        } else {
            return "Invaild Server" + identifier + " " + isAvailable + " " + hasWaitingCustomer;
        }

    }
}

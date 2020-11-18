package cs2030.simulator;

public class ArriveEvent extends Event {
    /**
     * Arrive Event Constructor.
     *
     * @param customer the customer object
     */
    ArriveEvent(Customer customer) {
        // find a server is available (free)
        // -> if the result not empty, let this server serve the customer
        // -> no one is free, find a server that the waiting queue is not full
        // ->-> if the result is not empty, let the customer wait in the queue
        // ->-> nobody free and all queue is full, customer leave.
        super(0, customer,
            x -> new Pair<Shop, Event>(x,
                x.isAllFullQueued() ? new LeaveEvent(customer)
                    : (x.findAvailableServer().isPresent()
                    ? new ServeEvent(customer, 
                                    x.findAvailableServer().get().serve(customer), 
                                    customer.getArrivalTime())
                    : new WaitEvent(customer, x.findServerQueue(customer).get().wait(customer)))));
    }

    @Override
    public String toString() {
        double arriveTime = this.getCustomer().getArrivalTime();
        String id = this.getCustomer().getType();
        return String.format("%.3f %s arrives", arriveTime, id);
    }

}

package cs2030.simulator;

public class ArriveEvent extends Event {
    /**
     * Arrive Event Constructor.
     *
     * @param customer the customer object
     */

    ArriveEvent(Customer customer) {
        super(customer,
                x -> new Pair<Shop, Event>(x,
                        x.find(y -> y.isAvailable()).isPresent() ? x.find(y -> y.isAvailable()).get().serve(customer)
                                : x.find(y -> !y.isFull()).isPresent() ? x.find(y -> !y.isFull()).get().serve(customer)
                                        : new LeaveEvent(customer)));
    }

    @Override
    public String toString() {
        double arriveTime = this.getCustomer().getArrivalTime();
        int id = this.getCustomer().getId();
        return String.format("%.3f %d arrives", arriveTime, id);
    }

}

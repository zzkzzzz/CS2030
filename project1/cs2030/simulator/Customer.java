package cs2030.simulator;

public class Customer {
    private final int id;
    private final double arrivalTime;

    /**
     * Customer Constructor.
     *
     * @param id          the customer id
     * @param arrivalTime the arrival time of the customer
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.1f", id, arrivalTime);
    }
}
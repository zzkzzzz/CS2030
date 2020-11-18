package cs2030.simulator;

import java.util.Optional;
import java.util.function.Supplier;

public class Customer {
    private final int id;
    private final double arrivalTime;
    private Optional<Double> serviceTime;
    private final Supplier<Double> serviceTimeSupplier;
    private final boolean isGreedy;

    /**
     * Customer Constructor.
     *
     * @param id          the customer id
     * @param arrivalTime the arrival time of the customer
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = Optional.empty();
        this.serviceTimeSupplier = () -> 1.0;
        this.isGreedy = false;
    }

    /**
     * Customer Constructor.
     *
     * @param id                  the customer id
     * @param arrivalTime         the arrival time of the customer
     * @param serviceTimeSupplier supplier of the service time
     * @param isGreedy            customer is greedy customer or typical customer
     * 
     */
    Customer(int id, double arrivalTime, Supplier<Double> serviceTimeSupplier, boolean isGreedy) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = Optional.empty();
        this.serviceTimeSupplier = serviceTimeSupplier;
        this.isGreedy = isGreedy;
    }

    /**
     * Customer Constructor.
     *
     * @param id          the customer id
     * @param serviceTime the arrival time of the customer
     */
    Customer(Customer customer, double serviceTime) {
        this.id = customer.getId();
        this.arrivalTime = customer.getArrivalTime();
        this.serviceTime = Optional.of(serviceTime);
        this.serviceTimeSupplier = null;
        this.isGreedy = false;
    }

    /**
     * Make the service time lazy, service time should be generated when the
     * customer is being served.
     * 
     * @return service time
     */
    public double get() {
        // if the value is available， get the value
        // if not, get the result of the serviceTimeSupplier
        double v = this.serviceTime.orElseGet(serviceTimeSupplier);
        this.serviceTime = Optional.of(v);
        return v;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public int getId() {
        return id;
    }

    public boolean isGreedy() {
        return this.isGreedy;
    }

    /**
     * String representation of the customer.
     * 
     * @return string representation of the customer
     */
    public String getType() {
        if (isGreedy) {
            return String.format("%d(greedy)", id);
        } else {
            return String.format("%d", id);
        }
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.1f", id, arrivalTime);
    }
}
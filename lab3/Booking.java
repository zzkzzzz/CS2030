public class Booking {
    private final Double bestFare;
    private final Driver driver;
    private final Service service;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.service = this.getBestService(driver, request);
        this.bestFare = service.computeFare(request) / 100.0;

    }

    public Double getBestFare() {
        return bestFare;
    }

    public int getDriverWaitTime() {
        return getDriver().getWaitingTime();
    }

    public Driver getDriver() {
        return driver;
    }

    public Service getService() {
        return service;
    }

    public Service getBestService(Driver driver, Request request) {
        Service[] services = driver.getServices();
        int min = (int) Integer.MAX_VALUE;
        Service bestService = null;
        for (Service tempService : services) {
            if (tempService.computeFare(request) < min) {
                min = tempService.computeFare(request);
                bestService = tempService;
            }
        }
        return bestService;
    }

    public int compareTo(Booking anotherBooking) {
        if (this.getBestFare().equals(anotherBooking.getBestFare()))
            return this.getDriverWaitTime() - anotherBooking.getDriverWaitTime();
        else
            return (int) Math.ceil(this.getBestFare() - anotherBooking.getBestFare());

    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", this.getBestFare(), this.getDriver().toString(),
                this.getService().toString());
    }
}

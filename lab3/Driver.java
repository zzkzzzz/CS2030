abstract class Driver {
    private final String license;
    private final int waitingTime;
    private final Service[] services;

    Driver(String license, int waitingTime, Service[] services) {
        this.license = license;
        this.waitingTime = waitingTime;
        this.services = services;
    }

    public String getLicense() {
        return license;
    }

    public Service[] getServices() {
        return services;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) Driver  ", this.license, this.waitingTime);
    }

}

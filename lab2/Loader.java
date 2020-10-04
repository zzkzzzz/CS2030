package lab2;

public class Loader {
    private final int identifier;
    private final Cruise cruise;

    Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    public int getIdentifier() {
        return identifier;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    public boolean canServe(Cruise cruise) {
        if (cruise.getArrivalTime() >= this.getNextAvailableTime())
            return true;
        else
            return false;
    }

    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise))
            return new Loader(this.identifier, cruise);
        else
            return this;
    }

    @Override
    public String toString() {
        return "Loader " + this.identifier + " serving " + this.cruise;
    }
}

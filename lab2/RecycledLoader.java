package lab2;

public class RecycledLoader extends Loader {
    RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise);
    }

    @Override
    public int getIdentifier() {
        return super.getIdentifier();
    }

    @Override
    public Cruise getCruise() {
        return super.getCruise();
    }

    @Override
    public int getNextAvailableTime() {
        // recycled loaders will go through a 60-minute long maintenance
        return super.getNextAvailableTime() + 60;
    }

    @Override
    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise))
            return new RecycledLoader(this.getIdentifier(), cruise);
        else
            return this;
    }

    @Override
    public String toString() {
        return "Recycled Loader " + this.getIdentifier() + " serving " + this.getCruise();

    }
}
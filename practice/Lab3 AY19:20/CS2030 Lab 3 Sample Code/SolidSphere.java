/**
 * @author Kevin Lim
 */
public class SolidSphere extends Sphere implements Solid {

    private final double density;

    public SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public double getMass() {
        return getVolume() * density;
    }

    @Override
    public SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, density);
    }

    @Override
    public String toString() {
        return String.format("SolidSphere [%.02f] with a mass of %.02f", radius, getMass());
    }

}

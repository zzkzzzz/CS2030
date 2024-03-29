/**
 * @author Kevin Lim
 */
public class SolidCuboid extends Cuboid implements Solid {

    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
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
    public SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, width, length, density);
    }

    @Override
    public SolidCuboid setWidth(double width) {
        return new SolidCuboid(height, width, length, density);
    }

    @Override
    public SolidCuboid setLength(double length) {
        return new SolidCuboid(height, width, length, density);
    }

    @Override
    public String toString() {
        return String.format("SolidCuboid [%.02f x %.02f x %.02f] with a mass of %.02f", height,
                width, length, getMass());
    }

}

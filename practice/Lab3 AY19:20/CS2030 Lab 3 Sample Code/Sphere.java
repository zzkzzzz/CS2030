/**
 * @author Kevin Lim
 */
public class Sphere implements Shape3D {

    protected final double radius;

    Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * radius * radius * radius;
    }

    @Override
    public double getSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    public Sphere setRadius(double radius) {
        return new Sphere(radius);
    }

    @Override
    public String toString() {
        return String.format("Sphere [%.02f]", radius);
    }

}

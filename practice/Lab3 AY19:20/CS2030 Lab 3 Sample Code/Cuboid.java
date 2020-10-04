/**
 * @author Kevin Lim
 */
public class Cuboid implements Shape3D {

    protected final double height;
    protected final double width;
    protected final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    @Override
    public double getVolume() {
        return height * width * length;
    }

    @Override
    public double getSurfaceArea() {
        return 2 * ((height * width) + (height * length) + (width * length));
    }

    public Cuboid setHeight(double height) {
        return new Cuboid(height, width, length);
    }

    public Cuboid setWidth(double width) {
        return new Cuboid(height, width, length);
    }

    public Cuboid setLength(double length) {
        return new Cuboid(height, width, length);
    }

    @Override
    public String toString() {
        return String.format("Cuboid [%.02f x %.02f x %.02f]", height, width, length);
    }

}

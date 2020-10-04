/**
 * @author Kevin Lim
 */
public class SolidShape3D {

    private final Shape3D shape;
    private final Material material;

    SolidShape3D(Shape3D shape, Material material) {
        this.shape = shape;
        this.material = material;
    }

    public double getMass() {
        return shape.getVolume() * getDensity();
    }

    public double getDensity() {
        return material.getDensity();
    }

    @Override
    public String toString() {
        return String.format("Solid%s with a mass of %.02f", shape.toString(), getMass());
    }

}

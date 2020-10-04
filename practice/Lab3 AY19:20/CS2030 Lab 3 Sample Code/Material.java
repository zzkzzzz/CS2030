/**
 * @author Kevin Lim
 */
public class Material {

    private final String name;
    private final double density;

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public double getDensity() {
        return density;
    }

}

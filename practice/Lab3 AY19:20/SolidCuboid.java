public class SolidCuboid extends Cuboid {
	private double density;

	SolidCuboid(double height, double width, double length, double density) {
		super(height, width, length);
		this.density = density;
	}

	public SolidCuboid setHeight(double newHeight) {
		return new SolidCuboid(newHeight, this.getWidth(), this.getLength(), this.density);
	}

	public SolidCuboid setWidth(double newWidth) {
		return new SolidCuboid(this.getHeight(), newWidth, this.getLength(), this.density);
	}

	public SolidCuboid setLength(double newLength) {
		return new SolidCuboid(this.getHeight(), this.getWidth(), newLength, this.density);
	}

	public double getMass() {
		return this.getVolume() * density;
	}

	public double getDensity() {
		return this.density;
	}

	@Override
	public String toString() {
		return String.format("SolidCuboid [%.2f x %.2f x %.2f] with a mass of %.2f", this.getHeight(), this.getWidth(),
				this.getLength(), this.getMass());
	}

}

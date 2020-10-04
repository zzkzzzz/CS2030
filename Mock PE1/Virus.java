public abstract class Virus {
	private String name;
	private double probabilityOfMutating;

	Virus(String name, double probabilityOfMutating) {
		this.name = name;
		this.probabilityOfMutating = probabilityOfMutating;
	}

	public abstract Virus spread(double random);

	public double getProbabilityOfMutating() {
		return probabilityOfMutating;
	}

	public String getName() {
		return name;

	}

	@Override
	public String toString() {
		return String.format("%s with %.3f probaility of mutating", this.name, this.probabilityOfMutating);
	}

}

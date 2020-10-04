public class SolidSphere extends Sphere{
	private double density;
	SolidSphere(double radius,double density){
		super(radius);
		this.density=density;
	}

	public double getDensity(){
		return this.density;
	}

	public SolidSphere setRadius(double newRadius){

		return new SolidSphere(newRadius,this.density); 
	}

	public double getMass(){
		return this.getVolume()*density;
	}
	@Override
	public String toString(){
		return String.format("Sphere [%.2f] with a mass of %.2f",this.getRadius(),this.getMass());
	}
}

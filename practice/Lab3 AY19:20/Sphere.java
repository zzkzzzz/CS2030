public class Sphere extends Shape{
	private double radius;
	Sphere(double radius){
		this.radius = radius;
	}

	public double getRadius(){
		return this.radius;
	}

	public Sphere setRadius(double newRadius){

		return new Sphere(newRadius);
	}

	public double getVolume(){
		return (4.0/3.0)*Math.PI*Math.pow(radius,3);
	}

	public double getSurfaceArea(){
		return 4*Math.PI*Math.pow(radius,2);
	}
	@Override
	public String toString(){
		return String.format("Sphere [%.2f]",this.radius);
	}
}	

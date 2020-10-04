public class SolidShape3D{
	private Shape shape;
	private Material material;

	SolidShape3D(Shape shape,Material material){
		this.shape = shape;
		this.material = material;
	}

	public double getDensity(){
		return this.material.getDensity();
	}

	public double getMass(){
		return this.material.getDensity()*this.shape.getVolume();
	}
	
	@Override
	public String toString(){
		return String.format("Solid%s with a mass of %.2f",this.shape.toString(),this.getMass());
		

}}


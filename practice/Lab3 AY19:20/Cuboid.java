
public class Cuboid extends Shape {
	private double height;
	private double width;
	private double length;

	Cuboid(double height,double width,double length){
		this.length=length;
		this.height=height;
		this.width=width;
	}	
	public double getHeight() {
		return this.height;
	}
	public double getWidth(){
		return this.width;
	}
	public double getLength(){
		return this.length;
	}
	public Cuboid setHeight(double newHeight){
		return new Cuboid(newHeight,this.width,this.length);
	}
	public Cuboid setWidth(double newWidth){
		return new Cuboid(this.height,newWidth,this.length);
	}
	public Cuboid setLength(double newLength){
		return new Cuboid(this.height,this.width,newLength);
	}

	public double getVolume(){
		return height*width*length;
	}
	public double getSurfaceArea(){
		return 2*(height*width+height*length+width*length);
	}

	@Override
	public String toString(){
		return String.format("Cuboid [%.2f x %.2f x %.2f]",height,width,length);
	}
}

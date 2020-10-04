package lab1;

class Circle {
    private final Point centre;
    private final double radius;

    // constructor
    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    // check circle contains point or not
    boolean contains(Point point) {
        return centre.distanceTo(point) <= this.radius;
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centered at " + this.centre;
    }

}
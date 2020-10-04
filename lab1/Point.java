package lab1;

class Point {
    private final double x;
    private final double y;

    // constructor
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // getter for x
    double getX() {
        return this.x;
    }

    // getter for y
    double getY() {
        return this.y;
    }

    // return the distance from this to otherpoint
    double distanceTo(Point otherpoint) {
        double dispX = this.x - otherpoint.x;
        double dispY = this.y - otherpoint.y;
        return Math.sqrt(dispX * dispX + dispY * dispY);
    }

    // return the middle Point of this.point and point p
    Point midPoint(Point q) {
        return new Point((this.x + q.getX()) / 2, (this.y + q.getY()) / 2);
    }

    // return the angle of line pq
    double angleTo(Point q) {
        return Math.atan2(q.getY() - this.y, q.getX() - this.x);
    }

    // return a new Point that move this point to (theta,d) in Polar Coodinate
    Point moveTo(double theta, double d) {
        double newX = (this.getX() + d * Math.cos(theta));
        double newY = (this.getY() + d * Math.sin(theta));

        return new Point(newX, newY);
    }

    @Override
    public String toString() {
        return "point (" + String.format("%.3f", this.x) + ", " + String.format("%.3f", this.y) + ")";
    }
}
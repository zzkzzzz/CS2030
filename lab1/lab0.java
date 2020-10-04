// package lab;

// class Point {
// private final double x;
// private final double y;

// // constructor
// Point(double x, double y) {
// this.x = x;
// this.y = y;
// }

// // getter for x
// double getX() {
// return this.x;
// }

// // getter for y
// double getY() {
// return this.y;
// }

// // return the distance from this to otherpoint
// double distanceTo(Point otherpoint) {
// double dispX = this.x - otherpoint.x;
// double dispY = this.y - otherpoint.y;
// return Math.sqrt(dispX * dispX + dispY * dispY);
// }

// // return the middle Point of this.point and point p
// Point midPoint(Point q) {
// return new Point((this.x + q.getX()) / 2, (this.y + q.getY()) / 2);
// }

// // return the angle of line pq
// double angleTo(Point q) {
// return Math.atan2(q.getY() - this.y, q.getX() - this.x);
// }

// // return a new Point that move this point to (theta,d) in Polar Coodinate
// Point moveTo(double theta, double d) {
// double newX = (this.getX() + d * Math.cos(theta));
// double newY = (this.getY() + d * Math.sin(theta));

// return new Point(newX, newY);
// }

// @Override
// public String toString() {
// return "point (" + String.format("%.3f", this.x) + ", " +
// String.format("%.3f", this.y) + ")";
// }
// }

// class Circle {
// private final Point centre;
// private final double radius;

// // constructor
// Circle(Point centre, double radius) {
// this.centre = centre;
// this.radius = radius;
// }

// // check circle contains point or not
// boolean contains(Point point) {
// return centre.distanceTo(point) <= this.radius;
// }

// @Override
// public String toString() {
// return "circle of radius " + this.radius + " centered at " + this.centre;
// }

// }

// class lab0 {

// Circle createUnitCircle(Point p, Point q) {
// double theta = p.angleTo(q);
// Point midPoint = p.midPoint(q);
// // square of the distance between middle point to q
// double mpSquare = Math.pow((midPoint.getX() - p.getX()), 2) +
// Math.pow((midPoint.getY() - p.getY()), 2);
// // distance between Center of circle to middle point
// double d = Math.sqrt(1 - mpSquare);

// return new Circle(midPoint.moveTo(theta + 0.5 * Math.PI, d), 1);
// }

// int findMaxDiscCoverage(Point[] points) {
// int maxDiscCoverage = 0;
// int count = 0;

// for (int i = 0; i < points.length - 1; i++) {
// for (int j = i + 1; j < points.length; j++) {
// // find coverage with (points[i], points[j])

// // if distance btw points[i] and points[j] larger than 2
// // then can not make a unit circle
// if (points[i].distanceTo(points[j]) > 2) {
// continue;
// }
// // make a unit circle
// Circle unitCircle = createUnitCircle(points[i], points[j]);

// // loop all point to find the coverage points
// for (int q = 0; q < points.length; q++) {
// if (unitCircle.contains(points[q])) {
// count++;
// }
// }
// maxDiscCoverage = Math.max(maxDiscCoverage, count);
// count = 0;
// }
// }
// return maxDiscCoverage;
// }

// public static void main(String[] args) {
// lab0 solution = new lab0();
// Point point1 = new Point(0.000, -1.000);
// Point point2 = new Point(1.000, 0.000);
// Point point3 = new Point(0.000, 1.000);
// Point point4 = new Point(-1.000, 0.000);

// Point point5 = new Point(0.100, 0.100);

// Point[] points = new Point[] { point1, point2, point3, point4, point5 };
// System.out.println(solution.findMaxDiscCoverage(points));
// }
// }

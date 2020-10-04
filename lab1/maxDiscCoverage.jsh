Circle createUnitCircle(Point p, Point q) {
        double theta = p.angleTo(q);
        Point midPoint = p.midPoint(q);
        // square of the distance between middle point to q
        double mpSquare = Math.pow((midPoint.getX() - p.getX()), 2) + Math.pow((midPoint.getY() - p.getY()), 2);
        // distance between Center of circle to middle point
        double d = Math.sqrt(1 - mpSquare);

       return new Circle(midPoint.moveTo(theta + 0.5 * Math.PI, d), 1);
    }


    int findMaxDiscCoverage(Point[] points) {
        int maxDiscCoverage = 0;
        int count = 0;

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // find coverage with (points[i], points[j])

                // if distance btw points[i] and points[j] larger than 2
                // then can not make a unit circle
                if (points[i].distanceTo(points[j]) > 2) {
                    continue;
                }
                // make a unit circle
                Circle unitCircle = createUnitCircle(points[i], points[j]);

                // loop all point to find the coverage points
                for (int q = 0; q < points.length; q++) {
                    if (unitCircle.contains(points[q])) {
                        count++;
                    }
                }
                maxDiscCoverage = Math.max(maxDiscCoverage, count);
                count = 0;
            }
        }
        return maxDiscCoverage;
    }

package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{

    ArrayList<Point> points;
    public NaivePointSet(List<Point> p) {
        points = new ArrayList<>();
        points.addAll(p);
    }

    @Override
    public Point nearest(double x, double y) {
        int N = points.size();
        double bestD = Double.POSITIVE_INFINITY, d;
        Point best = null, target;
        target = new Point(x, y);
        for (Point p : points) {
            d = Point.distance(p, target);
            if (d < bestD) {
                bestD = d;
                best = p;
            }
        }
        return best;
    }
}

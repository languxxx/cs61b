package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet {

    private static class Node implements Comparator<Node>{

        Point p;
        Node left, right;
        boolean compFlag; // ture for comparing X, false for comparing Y

        public Node(Point point, boolean flag) {
            p = point;
            compFlag = flag;
            left = null;
            right = null;
        }

        public double getX() {
            return p.getX();
        }

        public double getY() {
            return p.getY();
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public boolean getCompFlag() {
            return compFlag;
        }

        public Point getPoint() {
            return p;
        }

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getCompFlag())
                return Double.compare(o1.getX(), o2.getX());
            else return Double.compare(o1.getY(), o2.getY());
        }

        public int compareTo(Point p) {
            if (compFlag)
                return Double.compare(getX(), p.getX());
            else return Double.compare(getY(), p.getY());
        }

        public void addLeft(Node n) {
            left = n;
        }

        public void addRight(Node n) {
            right = n;
        }

        public double distance(Point n) {
            return Point.distance(p, n);
        }

        public double edgeDistance(Point n) {
            if (compFlag) {
                return Math.pow(getX() - n.getX(), 2);
            }
            else {
                return Math.pow(getY() - n.getY(), 2);
            }
        }
    }

    private Node root;

    public KDTree(List<Point> points) {
        Node n;
        root = null;

        for (Point p : points) {
            if (root == null) {
                root = new Node(p, true);
            }
            addPoint(root, p);
        }
    }

    private void addPoint(Node parent, Point p) {

        if (parent.compareTo(p) > 0) {
            if (parent.getLeft() != null) {
                addPoint(parent.getLeft(), p);
            }
            else {
                parent.addLeft(new Node(p, !parent.getCompFlag()));
            }
        }
        else {
            if (parent.getRight() != null) {
                addPoint(parent.getRight(), p);
            }
            else {
                parent.addRight(new Node(p, !parent.getCompFlag()));
            }
        }

    }

    /*
    Pseudocode from lecture note:
    If n is null, return best
    If n.distance(goal) < best.distance(goal), best = n
    If goal < n (according to n’s comparator):
        goodSide = n.”left”Child
        badSide = n.”right”Child
    else:
        goodSide = n.”right”Child
        badSide = n.”left”Child
    best = nearest(goodSide, goal, best)
    If bad side could still have something useful
        best = nearest(badSide, goal, best)
    return best
     */
    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }

        Node goodSide, badSide;
        if (n.compareTo(goal) > 0) {
            goodSide = n.getLeft();
            badSide = n.getRight();
        }
        else {
            goodSide = n.getRight();
            badSide = n.getLeft();
        }

        best = nearest(goodSide, goal, best);

        if(best.distance(goal) > n.edgeDistance(goal)) {
            best = nearest(badSide, goal, best);
        }

        return best;
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearest(root, goal, root);
        return best.getPoint();
    }

}

package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testRandomPoints() {
        Random rand = new Random();
        List<Point> points = new ArrayList<>();
        double x, y;
        for (int i = 0; i < 1000; i++) {
            x = rand.nextDouble() * 100;
            y = rand.nextDouble() * 100;
            points.add(new Point(x, y));
        }

        NaivePointSet naivePS = new NaivePointSet(points);
        KDTree tree = new KDTree(points);

        for (int i = 0; i < 100; i++) {
            x = rand.nextDouble() * 100;
            y = rand.nextDouble() * 100;
            assertEquals(naivePS.nearest(x, y), tree.nearest(x, y));
        }
    }

    public static void testRuntime(int N, int M) {
        Random rand = new Random();
        List<Point> points = new ArrayList<>();
        double x, y;
        for (int i = 0; i < N; i++) {
            x = rand.nextDouble() * N;
            y = rand.nextDouble() * N;
            points.add(new Point(x, y));
        }

        long start, end;

        System.out.print("NaivePS initialization runtime for " + N + " points: ");
        start = System.currentTimeMillis();

        NaivePointSet naivePS = new NaivePointSet(points);

        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("KDTree initialization runtime for " + N + " points: ");
        start = System.currentTimeMillis();

        KDTree tree = new KDTree(points);

        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("NaivePS searching runtime for " + M + " points: ");
        start = System.currentTimeMillis();

        for (int i = 0; i < M; i++) {
            x = rand.nextDouble() * N;
            y = rand.nextDouble() * N;
            naivePS.nearest(x, y);
        }

        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("KDTree searching runtime for " + M + " points: ");
        start = System.currentTimeMillis();

        for (int i = 0; i < M; i++) {
            x = rand.nextDouble() * N;
            y = rand.nextDouble() * N;
            tree.nearest(x, y);
        }

        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");
    }
    
    public static void main(String[] args) {
        //jh61b.junit.textui.runClasses(KDTreeTest.class);
        testRuntime(100000, 10000);
    }
}

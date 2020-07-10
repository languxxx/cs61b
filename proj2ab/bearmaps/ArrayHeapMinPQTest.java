package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testArrayHeapBasics() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            heap.add(i,(double) i/10.0);
            assertTrue(heap.contains(i));
            assertEquals(heap.size(), i + 1);
            assertEquals((int)heap.getSmallest(), 0);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals((int) heap.removeSmallest(), i);
            assertFalse(heap.contains(i));
            assertEquals(heap.size(), 9 - i);
            if (i <= 8)
                assertEquals((int)heap.getSmallest(), i+1);
        }
    }

    @Test
    public void testArrayHeapChangePriority() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            heap.add(i,(double) i/10.0 + 0.1);
        }

        for (int i = 9; i >= 0; i--) {
            heap.changePriority(i, (double)(9 - i)/10.0);
            assertTrue(heap.contains(i));
            assertEquals((int)heap.getSmallest(), 9);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals((int) heap.removeSmallest(), 9 - i);
        }
    }

    public static void testRuntime(int N, int M) {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        System.out.print("ArrayHeapMinPQ add runtime for " + N + " operations: ");
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            heap.add(i,(double) i/(double) N + 0.1);
        }
        long end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        System.out.print("NaiveMinPQ runtime for " + N + " operations: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            naivePQ.add(i,(double) i/(double) N + 0.1);
        }
        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("ArrayHeapMinPQ changePriority runtime for " + M + " operations: ");
        start = System.currentTimeMillis();
        for (int i = N - 1 ; i >= N - M; i--) {
            heap.changePriority(i, (double) (N - 1 - i) / (double) N);
        }
        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("NaiveMinPQ changePriority runtime for " + M + " operations: ");
        start = System.currentTimeMillis();
        for (int i = N - 1 ; i >= N - M; i--) {
            naivePQ.changePriority(i, (double) (N - 1 - i) / (double) N);
        }
        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("ArrayHeapMinPQ removeSmallest runtime for " + M + " operations: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < M; i++) {
            heap.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");

        System.out.print("NaiveMinPQ removeSmallest runtime for " + M + " operations: ");
        start = System.currentTimeMillis();
        for (int i = 0; i < M; i++) {
            naivePQ.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.print((end - start)/1000.0 +  " seconds.\n");
    }

    public static void main(String[] args) {
        //jh61b.junit.textui.runClasses(ArrayHeapMinPQTest.class);
        testRuntime(1000000, 1000);
    }
}

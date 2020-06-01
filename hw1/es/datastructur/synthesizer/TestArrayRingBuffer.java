package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(9.3);
        arb.enqueue(15.1);   // 9.3  15.1
        arb.enqueue(31.2);   // 9.3  15.1  31.2
        assertFalse(arb.isFull());        // 9.3  15.1  31.2       (returns false)
        arb.enqueue(-3.1) ;  // 9.3  15.1  31.2  -3.1
        assertTrue(arb.isFull());        // 9.3  15.1  31.2  -3.1 (returns true)
        assertEquals(arb.dequeue(),9.3);
        assertEquals(arb.peek(),15.1);
    }

    @Test
    public void testIteration() {
        ArrayRingBuffer arb1 = new ArrayRingBuffer<Double>(5);
        ArrayRingBuffer arb2 = new ArrayRingBuffer<Double>(5);
        arb1.enqueue(9.3);
        arb1.enqueue(15.1);   // 9.3  15.1
        arb1.enqueue(31.2);   // 9.3  15.1  31.2

        assertFalse(arb1.equals(arb2));

        arb1.enqueue(-3.1) ;  // 9.3  15.1  31.2  -3.1
        arb2.enqueue(9.3);
        arb2.enqueue(15.1);   // 9.3  15.1
        arb2.enqueue(31.2);   // 9.3  15.1  31.2

        assertFalse(arb1.equals(arb2));

        arb2.enqueue(-3.1) ;  // 9.3  15.1  31.2  -3.1

        assertTrue(arb1.equals(arb2));
    }
    public void main() {
        //someTest();
        testIteration();
    }
}

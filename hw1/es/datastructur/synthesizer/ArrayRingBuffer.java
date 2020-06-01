package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;


public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T []) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) new RuntimeException("Ring buffer overflow");
        else {
            rb[last] = x;
            last++;
            fillCount++;
        }
        if (last >= rb.length) last = 0;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        T removed = null;
        if (isEmpty()) new RuntimeException("Ring buffer underflow");
        else {
            removed = rb[first];
            rb[first] = null;
            first++;
            fillCount--;
        }
        if (first >= rb.length) first = 0;
        return removed;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        T peeked = rb[first];
        return peeked;
    }

    public int capacity() {
        return rb.length;
    }

    public int fillCount() {
        return fillCount;
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;
        public ArraySetIterator() { wizPos = 0; }
        public boolean hasNext() { return wizPos < fillCount; }
        public T next() {
            int next = wizPos + first;
            if (next >= rb.length) {
                next = next - rb.length;
            }
            T returnItem = rb[next];
            wizPos += 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != ArrayRingBuffer.class) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer) o;
        if (other.fillCount() != fillCount) {
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<T> otherIter = other.iterator();
        int i = 0;
        while (thisIter.hasNext()) {
            if (!otherIter.next().equals(thisIter.next())) {
                System.out.println(i);
                return false;
            }
            i++;
        }
        //return true;
        return !thisIter.hasNext();
    }
}


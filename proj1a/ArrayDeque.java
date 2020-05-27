public class ArrayDeque<T> {
    private int size,nextFirst,nextLast;
    public T[] items;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T []) new Object[other.items.length];
        System.arraycopy(other,0,items,0,other.size);
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, nextLast);
        int endLength = items.length-nextFirst-1;
        if (nextFirst < items.length-1) {
            System.arraycopy(items, nextFirst+1, a, capacity-endLength, endLength);
        }
        items = a;
        nextFirst += capacity - items.length;
    }
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(items.length*2);
        }
        items[nextFirst] = item;
        nextFirst--;
        size++;
        nextFirst = checkIndex(nextFirst);
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(items.length*2);
        }
        items[nextLast] = item;
        nextLast++;
        size++;
        nextLast = checkIndex(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        int index;
        for (int i=0;i<size;i++) {
            index = i+nextFirst+1;
            index = checkIndex(index);
            StdOut.print(items[index]);
            System.out.print(" ");
        }
        StdOut.printf("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            nextFirst++;
            nextFirst = checkIndex(nextFirst);
            size--;
            T removed = items[nextFirst];
            items[nextFirst] = null;
            return removed;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        else {
            nextLast--;
            nextLast = checkIndex(nextLast);
            size--;
            T removed = items[nextLast];
            items[nextLast] = null;
            return removed;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else {
            int i = index + nextFirst + 1;
            i = checkIndex(i);
            return items[i];
        }
    }

    private int checkIndex(int index) {
        if (index >= items.length) {
            index -= items.length;
        }
        else if (index < 0) {
            index += items.length;
        }
        return index;
    }
}

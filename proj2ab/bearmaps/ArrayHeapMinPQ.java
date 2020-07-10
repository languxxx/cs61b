package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    ArrayList<Entry<T>> key;
    HashMap<T, Integer> map;
    int N;

    public ArrayHeapMinPQ() {
        N = 0;
        key = new ArrayList<>();
        map = new HashMap<>();
    }

    private static class Entry<T> {
        T item;
        double priority;

        public Entry(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        public double getPriority() {
            return priority;
        }

        public boolean compare(Entry<T> e) {
            if (e == null)
                return true;

            return this.priority >= e.getPriority();
        }

        public void changePriority(double newPriority) {
            this.priority = newPriority;
        }

        public boolean equals(Entry<T> other) {
            return this.item.equals(other.getItem());
        }
    }

    private int getParent(int k) {
        return (k - 1) / 2;
    }

    private int getLeftChild(int k) {
        return k * 2 + 1 ;
    }

    private int getRightChild(int k) {
        return k * 2 + 2;
    }

    private void swap(int id1, int id2) {
        Entry<T> e1 = key.get(id1);
        Entry<T> e2 = key.get(id2);
        key.set(id1,key.get(id2));
        key.set(id2,e1);

        map.replace(e1.getItem(), id2);
        map.replace(e2.getItem(), id1);
    }

    private void rearrangeUp(int k) {
        int parentId;
        Entry<T> parent, e;

        while(k > 0) {
            e = key.get(k);
            parentId = getParent(k);
            parent = key.get(parentId);

            if (e.compare(parent)) {
                break;
            }

            swap(k, parentId);
            k = parentId;
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists.");
        }

        this.N++;
        Entry<T> e = new Entry<>(item, priority);
        key.add(e);
        map.put(item, this.N - 1);

        rearrangeUp(this.N - 1);
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        return key.get(0).getItem();
    }

    private void rearrangeDown(int k) {
        int leftId, rightId;
        Entry<T> e, left, right;
        while (k < this.N) {
            leftId = getLeftChild(k);
            rightId = getRightChild(k);

            e = key.get(k);

            if (rightId < this.N) {
                left = key.get(leftId);
                right = key.get(rightId);

                if (left.compare(e) && right.compare(e)) {
                    break;
                }

                if (left.compare(right)) {
                    swap(k, rightId);
                    k = rightId;
                } else {
                    swap(k, leftId);
                    k = leftId;
                }
            } else if (leftId < this.N) {
                left = key.get(leftId);

                if (e.compare(left)) {
                    swap(k, leftId);
                }

                break;
            } else
                break;
        }
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (this.N <= 0) {
            throw new NoSuchElementException("Empty Heap.");
        }
        Entry<T> removed = key.get(0);
        swap(0,this.N - 1);
        key.remove(this.N - 1);
        map.remove(removed.getItem());
        this.N--;

        rearrangeDown(0);

        return removed.getItem();
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return this.N;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item))
            throw new NoSuchElementException();

        int k = map.get(item);

        Entry<T> e = key.get(k);
        e.changePriority(priority);

        Entry<T> parent, left, right;
        int parentId, leftId, rightId;

        parentId = getParent(k);
        leftId = getLeftChild(k);
        rightId = getRightChild(k);

        if (parentId >= 0) {
            parent = key.get(parentId);
            if (parent.compare(e)) {
                rearrangeUp(k);
            }
        }

        if (rightId < this.N) {
            left = key.get(leftId);
            right = key.get(rightId);
            if (e.compare(left) || e.compare(right)) {
                rearrangeDown(k);
            }
        }
        else if (leftId < this.N) {
            left = key.get(leftId);
            if (e.compare(left)) {
                swap(k, leftId);
            }
        }

    }

    public void printMap() {
        Set<Map.Entry<T, Integer>> set = map.entrySet();
        for (Map.Entry<T, Integer> e : set) {
            System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
        }
    }

    public void printHeap() {
        Entry<T> e;
        for (int i = 0; i < this.N; i++) {
            e = key.get(i);
            System.out.println("Item: " + e.getItem() + " Priority: " + e.getPriority());
        }
    }
}

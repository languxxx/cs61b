public class LinkedListDeque<T> implements Deque<T>{
    private int size = 0;
    public ListNode sentinel;
    public class ListNode{
        public T item;
        public ListNode prev;
        public ListNode next;
        public ListNode(T i, ListNode p, ListNode l) {
            item = i;
            prev = p;
            next = l;
        }
    }

    public LinkedListDeque() {
        sentinel = new ListNode(null,sentinel,sentinel);
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new ListNode(null,other.sentinel.prev,other.sentinel.next);
        for (int i=0;i<other.size();i++) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        ListNode first = new ListNode(item,sentinel,sentinel.next);
        if (size == 0) {
            sentinel.next = first;
            sentinel.prev = first;
        }
        else {
            sentinel.next.prev = first;
            sentinel.next = first;
        }
        size++;
    }

    public void addLast(T item) {
        ListNode last = new ListNode(item,sentinel.prev,sentinel);
        if (size == 0) {
            sentinel.next = last;
            sentinel.prev = last;
        }
        else {
            sentinel.prev.next = last;
            sentinel.prev = last;
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        ListNode obj = sentinel.next;
        StdOut.print(obj.item);
        obj = obj.next;
        for (int i=1;i<size;i++) {
            System.out.print(" ");
            StdOut.print(obj.item);
            obj = obj.next;
        }
        StdOut.printf("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            T removed = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size--;
            return removed;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        else {
            T removed = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return removed;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else {
            ListNode l = sentinel;
            for (int i=0;i<=index;i++) {
                l = l.next;
            }
            return l.item;
        }
    }

    public T getRecursive(int index) {
        // TODO: 2020/5/26
        return null;
    }
}

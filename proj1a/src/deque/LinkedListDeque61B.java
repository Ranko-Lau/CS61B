package deque;

import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B{
    private class Node{
        public T item;
        public Node next;
        public Node prev;
        public Node (T i, Node n, Node p){
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }

    private Node sentinal;
    private int size;

    public LinkedListDeque61B() {
        sentinal = new Node(null, sentinal, sentinal);
        size = 0;
    }

    @Override
    public void addFirst(Object x) {

    }

    @Override
    public void addLast(Object x) {

    }

    @Override
    public List toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object removeFirst() {
        return null;
    }

    @Override
    public Object removeLast() {
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object getRecursive(int index) {
        return null;
    }
}

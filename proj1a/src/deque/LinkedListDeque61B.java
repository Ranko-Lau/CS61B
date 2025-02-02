package deque;

import java.util.ArrayList;
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
        this.sentinal = new Node(null, null, null);
        this.sentinal.next = this.sentinal;
        this.sentinal.prev = this.sentinal;
        this.size = 0;
    }

    @Override
    public void addFirst(Object x) {
        this.sentinal.next = new Node((T) x, this.sentinal.next, this.sentinal);
        this.sentinal.next.next.prev = this.sentinal.next;
        this.size++;
    }

    @Override
    public void addLast(Object x) {
        this.sentinal.prev = new Node((T) x, this.sentinal, this.sentinal.prev);
        this.sentinal.prev.prev.next = this.sentinal.prev;
        this.size++;
    }

    @Override
    public List toList() {
        List<T> returnList = new ArrayList<>();
        Node p = this.sentinal;
        while (p.next != this.sentinal){
            p = p.next;
            returnList.add(p.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
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

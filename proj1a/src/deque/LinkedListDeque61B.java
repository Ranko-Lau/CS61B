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
        if (this.isEmpty())return null;
        else {
            Object temp = sentinal.next.item;
            sentinal.next = sentinal.next.next;
            sentinal.next.prev = sentinal;
            size--;
            return temp;
        }
    }

    @Override
    public Object removeLast() {
        if (this.isEmpty())return null;
        else {
            Object temp = sentinal.prev.item;
            sentinal.prev = sentinal.prev.prev;
            sentinal.prev.next = sentinal;
            size--;
            return temp;
        }
    }

    @Override
    public Object get(int index) {
        Node p = this.sentinal.next;
        if (index < 0 || index > this.size() - 1) {
            return null;
        }
        else{
            for (int i = 0; i < index; i++){
                p = p.next;
            }
            return p.item;
        }
    }

    @Override
    public Object getRecursive(int index) {
        Node p = this.sentinal.next;
        if (index < 0 || index > this.size() - 1) {
            return null;
        }
        return getRecursiveHelper(p, index);
    }

    private Object getRecursiveHelper(Node p, int i){
        if (i == 0) return p.item;
        else return getRecursiveHelper(p.next, i - 1);
    }
}
